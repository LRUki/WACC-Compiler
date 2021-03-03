package wacc.backend.visitor

import wacc.backend.CodeGenerator
import wacc.backend.translate.CLibrary
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
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
import wacc.frontend.ast.type.*

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
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(visit(ast.expr))
        val reg = CodeGenerator.seeLastUsedCalleeReg()
        val exprType = ast.expr.getRealType(ast.symTable)
        if (ast.expr is ArrayElemAST) {
            var memType: MemoryType? = null
            if (exprType == BaseTypeAST(BaseType.BOOL) || exprType == BaseTypeAST(BaseType.CHAR)) {
                memType = MemoryType.SB
            }
            instrs.add(LoadInstr(Condition.AL, memType, RegisterMode(reg), reg))
        }
        when (ast.action) {
            Action.EXIT -> {
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                instrs.add(BranchInstr(Condition.AL, Label("exit"), true))
            }
            Action.PRINT, Action.PRINTLN -> {
                when (exprType) {
                    is BaseTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        when (exprType.type) {
                            BaseType.INT -> {
                                CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_INT)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_INT.toString()), true))
                            }
                            BaseType.CHAR -> {
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.PUTCHAR.toString()), true))
                            }
                            BaseType.BOOL -> {
                                CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_BOOL)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_BOOL.toString()), true))
                            }
                            BaseType.STRING -> {
                                CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_STRING)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            }
                        }
                    }
                    is ArrayTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        if (exprType.type == BaseTypeAST(BaseType.CHAR)) {
                            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_STRING)
                        } else {
                            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                            CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                        }
                    }
                    is PairTypeAST, is AnyPairTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                        CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                    }
                }
                if (ast.action == Action.PRINTLN) {
                    CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_LN)
                    instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_LN.toString()), true))
                }
                CodeGenerator.freeCalleeReg()
            }
            Action.FREE -> {
//                val stackOffset = symTable.findOffsetInStack((expr as IdentAST).name)
//                instrs.add(LoadInstr(getNextFreeCalleeReg(), null, RegisterAddrWithOffset(Register.SP, stackOffset, false), Condition.AL))
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(CodeGenerator.seeLastUsedCalleeReg())))
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.FREE_PAIR.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.FREE_PAIR)
            }
            Action.RETURN -> {
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
            }
        }
        return instrs

    }

    override fun visitAssignStatAST(ast: AssignStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(visit(ast.rhs))
        val calleeReg = CodeGenerator.seeLastUsedCalleeReg()
        if (ast.rhs is StrLiterAST) {
            ast.stringLabel = CodeGenerator.dataDirective.getStringLabel(ast.rhs.value)
        }

        val rhsType = ast.rhs.getRealType(ast.symTable)
        var memtype: MemoryType? = null
        if (rhsType is BaseTypeAST) {
            if (rhsType.type == BaseType.BOOL || rhsType.type == BaseType.CHAR) {
                memtype = MemoryType.B
            }
        }

        when (ast.rhs) {
            // only other RHS which requires "setting up"
            is CallRhsAST -> {
                var offset = 0
                if (ast.lhs is IdentAST) {
                    offset = ast.symTable.findOffsetInStack(ast.lhs.name)
                }
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, offset, false), calleeReg))
                CodeGenerator.freeCalleeReg()
                return instrs
            }
            is PairElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(calleeReg), calleeReg))
            }
        }

        ast.symTable.decreaseOffset(ast.lhs, rhsType)
        when (ast.lhs) {
            is IdentAST -> {
                val (correctSTScope, offset) = ast.symTable.getSTWithIdentifier(ast.lhs.name, rhsType)
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, correctSTScope.findOffsetInStack(ast.lhs.name) + offset, false), calleeReg))
            }
            is ArrayElemAST -> {
                instrs.addAll(visit(ast.lhs))
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), calleeReg))
                CodeGenerator.freeCalleeReg()
            }
            is PairElemAST -> {
                instrs.addAll(visit(ast.lhs))
//                instrs.add(LoadInstr(Condition.AL,null, RegisterAddr(seeLastUsedCalleeReg()), seeLastUsedCalleeReg()))
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), calleeReg))
                CodeGenerator.freeCalleeReg()
//                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
//                instrs.add(BranchInstr(Condition.AL, RuntimeError.nullReferenceLabel, true))
//                instrs.add(LoadInstr(Condition.AL, null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
//                instrs.add(StoreInstr(Condition.AL, null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
            }
//                instruction.add(StoreInstr(getNextFreeCalleeReg(), null, RegisterAddr(seeLastUsedCalleeReg()), Condition.AL))
//                freeCalleeReg()
//                instruction.add(StoreInstr(Condition.AL, null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
//                freeCalleeReg()

//                instruction.add(LoadInstr(seeLastUsedCalleeReg(),null, RegisterAddr(Register.R0), Condition.AL))
//                LDR r4, =42
        }

        CodeGenerator.freeCalleeReg()

        return instrs

    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(visit(ast.rhs))
        if (ast.rhs is StrLiterAST) {
            ast.stringLabel = CodeGenerator.dataDirective.getStringLabel(ast.rhs.value)
        }
        ast.symTable.decreaseOffset(ast.ident, ast.rhs.getRealType(ast.symTable))
        var memtype: MemoryType? = null
        when (ast.type) {
            is BaseTypeAST -> {
                if (ast.type.type == BaseType.BOOL || ast.type.type == BaseType.CHAR) {
                    memtype = MemoryType.B
                }
            }
            is ArrayTypeAST -> {

            }
            is PairTypeAST -> {
                if (ast.rhs !is NewPairRhsAST && ast.rhs !is ArrayElemAST && ast.rhs !is IdentAST &&
                        ast.rhs !is NullPairLiterAST && ast.rhs !is CallRhsAST && ast.rhs !is PairElemAST) {
                    instrs.add(LoadInstr(Condition.AL, null, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), CodeGenerator.seeLastUsedCalleeReg()))
                }
            }
        }
        when (ast.rhs) {
            is PairElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), CodeGenerator.seeLastUsedCalleeReg()))
            }
            is ArrayElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), CodeGenerator.seeLastUsedCalleeReg()))
            }
        }
        instrs.add(StoreInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, ast.symTable.offsetSize, false), Register.R4))
        CodeGenerator.freeCalleeReg()

        return instrs
    }

    override fun visitReadStatAST(ast: ReadStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        when (ast.expr) {
            is IdentAST -> {
                val (correctSTScope, offset) = ast.symTable.getSTWithIdentifier(ast.expr.name, (ast.exprType as BaseTypeAST))
                instrs.add(AddInstr(Condition.AL, Register.R4, Register.SP, ImmediateIntOperand(correctSTScope.findOffsetInStack(ast.expr.name) + offset)))
            }
            is ArrayElemAST -> {
                // Handled elsewhere
            }
            is PairElemAST -> {
                instrs.addAll(visit(ast.expr))
            }
        }
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R4)))

        when ((ast.exprType as BaseTypeAST).type) {
            BaseType.INT -> {
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_INT.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.READ_INT)
            }
            BaseType.CHAR -> {
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_CHAR.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.READ_CHAR)
            }
            else -> throw RuntimeException("Read can only be used for int or char, semantic check failed")
        }
        return instrs
    }

    override fun visitSkipStatAST(ast: SkipStatAST): List<Instruction> {
        return emptyList()
    }

    override fun visitMultiStatAST(ast: MultiStatAST): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        ast.stats.forEach { instructions.addAll(visit(it)) }
        return instructions
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