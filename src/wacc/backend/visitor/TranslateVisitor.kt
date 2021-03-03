package wacc.backend.visitor

import wacc.backend.CodeGenerator
import wacc.backend.translate.CLibrary
import wacc.backend.translate.RuntimeError
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairChoice
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
                instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(Register.SP, offset, false), calleeReg))
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
                instrs.add(StoreInstr( memtype, RegisterAddrWithOffsetMode(Register.SP, correctSTScope.findOffsetInStack(ast.lhs.name) + offset, false), calleeReg))
            }
            is ArrayElemAST -> {
                instrs.addAll(visit(ast.lhs))
                instrs.add(StoreInstr( memtype, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), calleeReg))
                CodeGenerator.freeCalleeReg()
            }
            is PairElemAST -> {
                instrs.addAll(visit(ast.lhs))
//                instrs.add(LoadInstr(Condition.AL,null, RegisterAddr(seeLastUsedCalleeReg()), seeLastUsedCalleeReg()))
                instrs.add(StoreInstr(memtype, RegisterMode(CodeGenerator.seeLastUsedCalleeReg()), calleeReg))
                CodeGenerator.freeCalleeReg()
//                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
//                instrs.add(BranchInstr(Condition.AL, RuntimeError.nullReferenceLabel, true))
//                instrs.add(LoadInstr(Condition.AL, null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
//                instrs.add(StoreInstr(null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
            }
//                instruction.add(StoreInstr(getNextFreeCalleeReg(), null, RegisterAddr(seeLastUsedCalleeReg()), Condition.AL))
//                freeCalleeReg()
//                instruction.add(StoreInstr(null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
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
        instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(Register.SP, ast.symTable.offsetSize, false), Register.R4))
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
        val instrs = mutableListOf<Instruction>()
        var memtype: MemoryType? = null
        //Malloc space for pair
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(2 * 4), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        val stackReg = CodeGenerator.getNextFreeCalleeReg()
        instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //Malloc first element
        instrs.addAll(visit(ast.fst))
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(SymbolTable.getBytesOfType(ast.firstType)), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (ast.firstType == BaseTypeAST(BaseType.BOOL) // TODO: refactor this
                || ast.firstType == BaseTypeAST(BaseType.CHAR)) {
            memtype = MemoryType.B
        }
        instrs.add(StoreInstr(memtype, RegisterMode(Register.R0), CodeGenerator.seeLastUsedCalleeReg()))
        CodeGenerator.freeCalleeReg()
        instrs.add(StoreInstr(null, RegisterMode(stackReg), Register.R0))

        //Malloc second element
        instrs.addAll(visit(ast.snd))
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(SymbolTable.getBytesOfType(ast.secondType)), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (ast.secondType == BaseTypeAST(BaseType.BOOL) // TODO: refactor this
                || ast.secondType == BaseTypeAST(BaseType.CHAR)) {
            memtype = MemoryType.B
        }
        instrs.add(StoreInstr(memtype, RegisterMode(Register.R0), CodeGenerator.seeLastUsedCalleeReg()))
        CodeGenerator.freeCalleeReg()
        instrs.add(StoreInstr(null, RegisterAddrWithOffsetMode(stackReg, 4, false), Register.R0))

        return instrs

    }

    override fun visitCallRhsAST(ast: CallRhsAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val totalLength = ast.argTypes.size - 1
        var totalBytes = 0
        for ((index, arg) in ast.argList.reversed().withIndex()) {
            var memType: MemoryType? = null
            instrs.addAll(visit(arg))
            val bytes = SymbolTable.getBytesOfType(ast.argTypes[(totalLength - index)])
            ast.symTable.callOffset = bytes
            totalBytes += bytes
            if (ast.argTypes[(totalLength - index)] == BaseTypeAST(BaseType.BOOL) // TODO: refactor this
                    || ast.argTypes[(totalLength - index)] == BaseTypeAST(BaseType.CHAR)) {
                memType = MemoryType.B
            }
            instrs.add(StoreInstr(memType, RegisterAddrWithOffsetMode(Register.SP, -1 * bytes, true), CodeGenerator.seeLastUsedCalleeReg()))
            CodeGenerator.freeCalleeReg()
            if (index == 0) {
                ast.symTable.increaseOffsetForCall = 4
            }
        }
        ast.symTable.callOffset = 0
        ast.symTable.increaseOffsetForCall = 0

//        argList.reversed().forEach {
//            instrs.addAll(visit(it))
//            val bytes = getBytesOfType(it.getRealType(symTable))
//            instrs.add(StoreInstr(seeLastUsedCalleeReg(), null, RegisterAddrWithOffset(Register.SP, -1 * bytes, true), Condition.AL))
//            freeCalleeReg()
//        }

        val funcLabel = FunctionLabel(ast.ident.name)
        instrs.add(BranchInstr(Condition.AL, funcLabel, true))
        instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(totalBytes), false))
        instrs.add(MoveInstr(Condition.AL, CodeGenerator.getNextFreeCalleeReg(), RegisterOperand(Register.R0)))
        return instrs
    }

    override fun visitBinOpExprAST(ast: BinOpExprAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(visit(ast.expr1))
        var reg1 = CodeGenerator.seeLastUsedCalleeReg()
        instrs.addAll(visit(ast.expr2))
        var reg2 = CodeGenerator.seeLastUsedCalleeReg()

        var useAccumulator = false
        if (reg1 == Register.NONE || reg1 == Register.R10) {
            useAccumulator = true
            reg1 = Register.R10
            reg2 = Register.R11
        }

        when (ast.binOp) {
            BinOp.PLUS -> {
                if (!useAccumulator) {
                    instrs.add(AddInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(AddInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                }
                instrs.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.MINUS -> {
                if (!useAccumulator) {
                    instrs.add(SubInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(SubInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                }
                instrs.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.MULT -> {
                if (!useAccumulator) {
                    instrs.add(MultInstr(Condition.AL, reg1, reg2, reg1, reg2))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MultInstr(Condition.AL, reg1, reg2, reg2, reg1))
                }
                instrs.add(CompareInstr(reg2, RegShiftOffsetOperand(reg1, ShiftType.ASR, 31)))
                instrs.add(BranchInstr(Condition.NE, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.DIV -> {
                if (!useAccumulator) {
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg2)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg1)))
                }
                instrs.add(BranchInstr(Condition.AL, RuntimeError.divideZeroCheckLabel, true))
                CodeGenerator.runtimeErrors.addDivideByZeroCheck()
                instrs.add(BranchInstr(Condition.AL, Label("__aeabi_idiv"), true))
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R0)))
            }
            BinOp.MOD -> {
                if (!useAccumulator) {
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg2)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg1)))
                }
                instrs.add(BranchInstr(Condition.AL, RuntimeError.divideZeroCheckLabel, true))
                CodeGenerator.runtimeErrors.addDivideByZeroCheck()
                instrs.add(BranchInstr(Condition.AL, Label("__aeabi_idivmod"), true))
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R1)))
            }
            BinOp.EQ -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.EQ, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.NE, reg1, ImmediateBoolOperand(false)))
            }

            BinOp.NEQ -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.NE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.EQ, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.LTE -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.LT -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.GTE -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.GT -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(false)))
            }

            BinOp.AND -> {
                if (!useAccumulator) {
                    instrs.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))

                }
            }
            BinOp.OR -> {
                if (!useAccumulator) {
                    instrs.add(OrInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(OrInstrType(Condition.AL, reg1, reg2, RegisterOperand(reg1)))
                }
            }
        }
        if (!useAccumulator) {
            CodeGenerator.freeCalleeReg()
        }
        return instrs
    }

    override fun visitUnOpExprAST(ast: UnOpExprAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(visit(ast.expr))
        val reg1 = CodeGenerator.seeLastUsedCalleeReg()
        when (ast.unOp) {
            UnOp.NOT -> {
                instrs.add(XorInstrType(Condition.AL, reg1, reg1, ImmediateIntOperand(1)))
            }
            UnOp.MINUS -> {
                instrs.add(ReverseSubInstr(Condition.AL, reg1, reg1, ImmediateIntOperand(0), true))
                instrs.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            UnOp.LEN -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(Register.SP), reg1))
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg1), reg1))
//                instrs.add(LoadInstr(Condition.AL, null, ImmediateInt((expr as ArrayElemAST).indices.size), reg1))
//                  consider the case when expr is a variable
            }
            UnOp.ORD -> {
//                instrs.add(MoveInstr(Condition.AL, reg1,  ImmediateOperandChar((expr as CharLiterAST).value)))
            }
            UnOp.CHR -> {
//                instrs.add(LoadInstr(reg1, null, ImmediateInt((expr as IntLiterAST).value), Condition.AL))
            }
        }
        return instrs
    }

    override fun visitArrayElemAST(ast: ArrayElemAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val stackReg = CodeGenerator.getNextFreeCalleeReg()
        val stackOffset = ast.symTable.findOffsetInStack(ast.ident.name)
        instrs.add(AddInstr(Condition.AL, stackReg, Register.SP, ImmediateIntOperand(stackOffset), false))
        ast.indices.forEach {
            instrs.addAll(visit(it))
            instrs.add(LoadInstr(Condition.AL, null, RegisterMode(stackReg), stackReg))
            instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(CodeGenerator.seeLastUsedCalleeReg())))
            instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(stackReg)))
            instrs.add(BranchInstr(Condition.AL, RuntimeError.checkArrayBoundsLabel, true))
            CodeGenerator.runtimeErrors.addArrayBoundsCheck()
            instrs.add(AddInstr(Condition.AL, stackReg, stackReg, ImmediateIntOperand(4), false))
            val identType = ast.ident.getRealType(ast.symTable)
            if (identType is ArrayTypeAST &&
                    ((identType.type.equals(BaseTypeAST(BaseType.CHAR)) || identType.type.equals(BaseTypeAST(BaseType.BOOL))))) {
                instrs.add(AddInstr(Condition.AL, stackReg, stackReg, RegisterOperand(CodeGenerator.seeLastUsedCalleeReg()), false))
            } else {
                instrs.add(AddInstr(Condition.AL, stackReg, stackReg, RegShiftOffsetOperand(CodeGenerator.seeLastUsedCalleeReg(), ShiftType.LSL, 2), false))
            }
            CodeGenerator.freeCalleeReg()
        }
        return instrs

    }

    override fun visitPairElemAST(ast: PairElemAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        instrs.addAll(visit(ast.expr))
        val reg = CodeGenerator.seeLastUsedCalleeReg()
//        instrs.add(LoadInstr(Condition.AL, null, RegisterAddrWithOffset(Register.SP, SymbolTable.getBytesOfType(type), false), reg))
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
        instrs.add(BranchInstr(Condition.AL, RuntimeError.nullReferenceLabel, true))
        CodeGenerator.runtimeErrors.addNullReferenceCheck()
        if (ast.choice == PairChoice.FST) {
            instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg), reg))
        } else {
            instrs.add(LoadInstr(Condition.AL, null, RegisterAddrWithOffsetMode(reg, 4, false), reg))
        }
        return instrs
    }

    override fun visitIdentAST(ast: IdentAST): List<Instruction> {
        var offset = ast.symTable.findOffsetInStack(ast.name)
        var memType: MemoryType? = null
        val type = ast.getRealType(ast.symTable)
        if (type == BaseTypeAST(BaseType.BOOL) || type == BaseTypeAST(BaseType.CHAR)) {
            memType = MemoryType.SB
        }
        offset += ast.symTable.checkParamInFuncSymbolTable(ast.name) + ast.symTable.callOffset
        return listOf(LoadInstr(Condition.AL, memType, RegisterAddrWithOffsetMode(Register.SP, offset, false), CodeGenerator.getNextFreeCalleeReg()))

    }

    override fun visitIntLiterAST(ast: IntLiterAST): List<Instruction> {
        var reg = CodeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateIntMode(ast.value), reg)
        return instrs
    }

    override fun visitBoolLiterAST(ast: BoolLiterAST): List<Instruction> {
        var reg = CodeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateBoolOperand(ast.value))
        return instrs
    }

    override fun visitStrLiterAST(ast: StrLiterAST): List<Instruction> {
        var reg = CodeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        val strLabel = CodeGenerator.dataDirective.addStringLabel(ast.value)
        instrs += LoadInstr(Condition.AL, null, ImmediateLabelMode(strLabel), reg)
        return instrs
    }

    override fun visitCharLiterAST(ast: CharLiterAST): List<Instruction> {
        var reg = CodeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateCharOperand(ast.value))
        return instrs
    }

    override fun visitNullPairLiterAST(ast: NullPairLiterAST): List<Instruction> {
        var reg = CodeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateIntMode(0), reg)
        return instrs
    }

    override fun visitArrayLiterAST(ast: ArrayLiterAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val elemSize = SymbolTable.getBytesOfType((ast.arrayType as ArrayTypeAST).type)
//        println("This is the bytes of ${arrayType} ${elemSize}")
        //loading the length of array * elemSize + size of INT
        val sizeOfInt = SymbolTable.getBytesOfType(BaseTypeAST(BaseType.INT))
        instrs.add(LoadInstr(Condition.AL, null,
                ImmediateIntMode(elemSize * ast.values.size + sizeOfInt), Register.R0))

        instrs.add(BranchInstr(Condition.AL, Label("malloc"), true))
        val stackReg = CodeGenerator.getNextFreeCalleeReg()
        instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //add element to stack
        var memType: MemoryType? = null
        for ((i, expr) in ast.values.withIndex()) {
            if (expr is IdentAST) {
                expr.symTable = ast.symTable
            }
            instrs.addAll(visit(expr))

            if ((expr is CharLiterAST) || (expr is BoolLiterAST)) {
                memType = MemoryType.B
            }
            instrs.add(StoreInstr(memType,
                    RegisterAddrWithOffsetMode(stackReg,
                            sizeOfInt + (i * elemSize),
                            false), CodeGenerator.seeLastUsedCalleeReg()))
            CodeGenerator.freeCalleeReg()
        }

        //add the length of the array to stack
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(ast.values.size), CodeGenerator.getNextFreeCalleeReg()))
        instrs.add(StoreInstr(null,
                RegisterMode(stackReg), CodeGenerator.seeLastUsedCalleeReg()))
        CodeGenerator.freeCalleeReg()
        return instrs
    }

    override fun visitTypeAST(ast: TypeAST): List<Instruction> {
        return emptyList()
    }

}
