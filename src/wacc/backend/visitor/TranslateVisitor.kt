package wacc.backend.visitor

import wacc.backend.CodeGenerator
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.ImmediateIntMode
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.type.TypeAST

class TranslateVisitor: AstVisitor<List<Instruction>> {

    override fun visitProgramAST(ast: ProgramAST): List<Instruction> {
//      Translate function definitions
        val instrs = mutableListOf<Instruction>()
        //add some stuff here. directives, .globalMain, .data,.text
//
//        addMsg()
//        // add msg here
        instrs.add(DirectiveInstr("text"))
        instrs.add(DirectiveInstr("global main"))
        ast.funcList.forEach { instrs.addAll(visit(it)) }

        instrs.add(Label("main"))
        // AI: PUSH {lr}
        instrs.add(PushInstr(Register.LR))
        ProgramAST.translateScoped(ast.symTable, instrs, ast.stats)
        // AI: LDR r0, =0
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(0), Register.R0))
        // AI: POP {pc}
        instrs.add(PopInstr(Register.PC))
        instrs.add(DirectiveInstr("ltorg"))

        val data = CodeGenerator.dataDirective.translate()
        val cLib = CodeGenerator.CLib.translate()
        val runtime = CodeGenerator.runtimeErrors.translate()
        // data + main + runtime err + clib calls
        return data + instrs + runtime + cLib
    }

    override fun visitFuncAST(ast: FuncAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.add(FunctionLabel(ast.ident.name))
        instrs.add(PushInstr(Register.LR))
        val stackOffset = ast.symTable.getStackOffset()
        ast.symTable.startingOffset = stackOffset
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        ast.body.forEach { instrs.addAll(visit(it)) }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
        instrs.add(DirectiveInstr("ltorg"))
        CodeGenerator.freeAllCalleeReg()
        return instrs
    }

    override fun visitParamAST(ast: ParamAST): List<Instruction> {
        return emptyList()
    }

    override fun visitBlockStatAST(ast: BlockStatAST): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        ProgramAST.translateScoped(ast.symTable, instr, ast.body)
        return instr
    }

    override fun visitIfStatAST(ast: IfStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val elseLabel = CodeGenerator.getNextLabel()
        val afterElseLabel = CodeGenerator.getNextLabel()

        instrs.addAll(visit(ast.cond))
        instrs.add(CompareInstr(CodeGenerator.seeLastUsedCalleeReg(), ImmediateIntOperand(0)))
        instrs.add(BranchInstr(Condition.EQ, elseLabel, false))
        CodeGenerator.freeCalleeReg()
        var stackOffset = ast.thenST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        ast.thenBody.forEach {
            instrs.addAll(visit(it))
        }
        val lastStat = ast.thenBody.last()
        if ((lastStat is ActionStatAST) && lastStat.action == Action.RETURN) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(ast.symTable.getFuncStackOffset())))
            instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
            CodeGenerator.freeAllCalleeReg()
        }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }

        instrs.add(BranchInstr(Condition.AL, afterElseLabel, false))
        instrs.add(elseLabel)

        stackOffset = ast.elseST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        ast.elseBody.forEach { instrs.addAll(visit(it)) }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        instrs.add(afterElseLabel)
        return instrs
    }

    override fun visitWhileStatAST(ast: WhileStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val condLabel = CodeGenerator.getNextLabel()
        val bodyLabel = CodeGenerator.getNextLabel()
        instrs.add(BranchInstr(Condition.AL, condLabel, false))

        instrs.add(bodyLabel)
        val stackOffset = ast.blockST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        ast.body.forEach { instrs.addAll(visit(it)) }
        instrs.add(condLabel)
        instrs.addAll(visit(ast.cond))
        instrs.add(CompareInstr(CodeGenerator.seeLastUsedCalleeReg(), ImmediateIntOperand(1)))
        instrs.add(BranchInstr(Condition.EQ, bodyLabel, false))
        CodeGenerator.freeCalleeReg()
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        return instrs
    }

    override fun visitActionStatAST(ast: ActionStatAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitAssignStatAST(ast: AssignStatAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitReadStatAST(ast: ReadStatAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitSkipStatAST(ast: SkipStatAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitMultiStatAST(ast: MultiStatAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitNewPairRhsAST(ast: NewPairRhsAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitCallRhsAST(ast: CallRhsAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitBinOpExprAST(ast: BinOpExprAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitUnOpExprAST(ast: UnOpExprAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitArrayElemAST(ast: ArrayElemAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitPairElemAST(ast: PairElemAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitIdentAST(ast: IdentAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitIntLiterAST(ast: IntLiterAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitBoolLiterAST(ast: BoolLiterAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitStrLiterAST(ast: StrLiterAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitCharLiterAST(ast: CharLiterAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitNullPairLiterAST(ast: NullPairLiterAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitArrayLiterAST(ast: ArrayLiterAST): List<Instruction> {
        TODO("Not yet implemented")
    }

    override fun visitTypeAST(ast: TypeAST): List<Instruction> {
        return emptyList()
    }

}