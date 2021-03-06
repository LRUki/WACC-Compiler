package wacc.backend.visitor

import wacc.WaccConfig
import wacc.backend.CodeGenerator
import wacc.backend.translate.CLibrary
import wacc.backend.translate.RuntimeErrors
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
import wacc.frontend.SymbolTable
import wacc.frontend.SymbolTable.Companion.getBytesOfType
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.StructAssignRhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairChoice
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.ForStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.struct.StructAccessAST
import wacc.frontend.ast.type.*
import java.util.stream.Collectors

/**
 * Visitor pattern for code generation.
 * Takes the top level program AST built by
 * the frontend and recursively generates code
 * based on different AST visit methods
 */
class TranslateVisitor(private val codeGenerator: CodeGenerator = CodeGenerator()) : AstVisitor<List<Instruction>> {

    private val pointerOffset = 4

    /** Utility function that deal with scope translation */
    private fun translateScoped(table: SymbolTable, instrs: MutableList<Instruction>, stats: List<StatAST>) {
        val MAX_STACK_OFFSET = 1024
        val stackOffset = table.getStackOffset()
        if (stackOffset > 0) {
            var stackOffsetLeft = stackOffset
            table.startingOffset = stackOffset
            while (stackOffsetLeft > MAX_STACK_OFFSET) {
                instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(MAX_STACK_OFFSET)))
                stackOffsetLeft -= MAX_STACK_OFFSET
            }
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffsetLeft)))
        }

        /** Visit each of the statements and add to their instructions to instrs */
        stats.forEach { instrs.addAll(this.visit(it)) }

        if (stackOffset > 0) {
            var stackOffsetLeft = stackOffset
            while (stackOffsetLeft > MAX_STACK_OFFSET) {
                instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(MAX_STACK_OFFSET)))
                stackOffsetLeft -= MAX_STACK_OFFSET
            }
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffsetLeft)))
        }
    }

    /** Top level translation function which translates a Program AST */
    override fun visitProgramAST(ast: ProgramAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        /** Sets up directives found at the start of the assembly file*/
        instrs.add(DirectiveInstr("text"))
        instrs.add(DirectiveInstr("global main"))

        /** Translates all of the function definitions (in parallel) */
        val listOfFuncInstrs = if (!WaccConfig.parallelCompile) {
            ast.funcList.stream()
        } else {
            ast.funcList.parallelStream()
        }
                .map { TranslateVisitor(codeGenerator.clone()).visit(it) }
                .collect(Collectors.toList())

        listOfFuncInstrs.forEach { instrs.addAll(it) }

        /** Translates each statement in the program */
        instrs.add(Label("main"))
        instrs.add(PushInstr(Register.LR))
        translateScoped(ast.symTable, instrs, ast.stats)
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(0), Register.R0))
        instrs.add(EndInstr())
        instrs.add(LTORGDirective())

        /** Translates all string labels, c library functions and runtime
         * errors that have been recursively found and added */
        val data = codeGenerator.dataDirective.translate()
        val cLib = codeGenerator.cLib.translate()
        val runtime = codeGenerator.runtimeErrors.translate()

        return data + instrs + runtime + cLib
    }

    /** Translates a Function AST */
    override fun visitFuncAST(ast: FuncAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.add(FunctionLabel(ast.ident.name))
        instrs.add(PushInstr(Register.LR))
        val stackOffset = ast.symTable.getStackOffset()
        ast.symTable.startingOffset = stackOffset
        /** Moves stack pointer down when variables are declared in the function scope */
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        ast.body.forEach { instrs.addAll(visit(it)) }

        /** Checks if the last statement is Exit of If with returns as the last in both branches */
        var returnedOrExited = false
        val lastStat = ast.body.last()
        if ((lastStat is IfStatAST) && lastStat.thenHasReturn && lastStat.elseHasReturn) {
            returnedOrExited = true
        } else if ((lastStat is ActionStatAST) && lastStat.action == Action.EXIT) {
            returnedOrExited = true
        }
        /** Returns stack pointer to value before the function */
        if (!returnedOrExited) {
            if (stackOffset > 0) {
                instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
            }
            instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
        }
        instrs.add(DirectiveInstr("ltorg"))
        codeGenerator.freeAllCalleeReg()
        return instrs
    }

    /** Translates a Parameter AST. Requires no code generation */
    override fun visitParamAST(ast: ParamAST): List<Instruction> {
        return emptyList()
    }

    /** Translates a Block Statement AST */
    override fun visitBlockStatAST(ast: BlockStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        translateScoped(ast.symTable, instrs, ast.body)
        return instrs
    }

    /** Checks if the final statement is return or exit.
     *  Returns stack pointer to original value when its a return */
    private fun addExitCodeForReturnStatement(body: List<StatAST>, table: SymbolTable): Pair<List<Instruction>, Boolean> {
        val instrs = mutableListOf<Instruction>()
        val lastStat = body.last()
        var hasReturn = false
        if ((lastStat is ActionStatAST)) {
            if (lastStat.action == Action.RETURN) {
                hasReturn = true
//                instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(table.getFuncStackOffset())))
//                instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
//                codeGenerator.freeAllCalleeReg()
            }
            if (lastStat.action == Action.EXIT) {
                hasReturn = true
            }
        } else if ((lastStat is VoidReturnStatAST)) {
            hasReturn = true
//            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(table.getFuncStackOffset())))
//            instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
//            codeGenerator.freeAllCalleeReg()
        }
        return Pair(instrs, hasReturn)
    }

    /** Translates an If Statement AST */
    override fun visitIfStatAST(ast: IfStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val elseLabel = codeGenerator.getNextLabel()
        val afterElseLabel = codeGenerator.getNextLabel()

        /** Translates the condition of the If */
        instrs.addAll(visit(ast.cond))
        instrs.add(CompareInstr(codeGenerator.seeLastUsedCalleeReg(), ImmediateIntOperand(0)))
        instrs.add(BranchInstr(Condition.EQ, elseLabel, false))
        codeGenerator.freeCalleeReg()
        var stackOffset = ast.thenST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        /** Translates all statements in the then branch */
        ast.thenBody.forEach {
            instrs.addAll(visit(it))
        }
        /** Checks if final statement in then branch is a return and sets variable for use by FuncAST */
        val (thenInstr, thenHasReturn) = addExitCodeForReturnStatement(ast.thenBody, ast.thenST)
        instrs.addAll(thenInstr)
        ast.thenHasReturn = thenHasReturn
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }

        instrs.add(BranchInstr(Condition.AL, afterElseLabel, false))
        instrs.add(elseLabel)
        stackOffset = ast.elseST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        /** Translates all statements in the else branch */
        ast.elseBody.forEach { instrs.addAll(visit(it)) }

        /** Checks if final statement in else branch is a return and sets variable for use by FuncAST */
        val (elseInstr, elseHasReturn) = addExitCodeForReturnStatement(ast.elseBody, ast.elseST)
        instrs.addAll(elseInstr)
        ast.elseHasReturn = elseHasReturn
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        instrs.add(afterElseLabel)
        return instrs
    }

    /** Translates a While Statement AST */
    override fun visitWhileStatAST(ast: WhileStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val condLabel = codeGenerator.getNextLabel()
        val bodyLabel = codeGenerator.getNextLabel()
        instrs.add(BranchInstr(Condition.AL, condLabel, false))

        instrs.add(bodyLabel)
        val stackOffset = ast.blockST.getStackOffset()
        ast.blockST.startingOffset = stackOffset
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        /** Translates all the statements within the while loop body */
        ast.body.forEach { instrs.addAll(visit(it)) }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        /** Translates the condition after the loop body.*/
        instrs.add(condLabel)
        instrs.addAll(visit(ast.cond))
        instrs.add(CompareInstr(codeGenerator.seeLastUsedCalleeReg(), ImmediateIntOperand(1)))
        instrs.add(BranchInstr(Condition.EQ, bodyLabel, false))
        codeGenerator.freeCalleeReg()
        return instrs
    }

    override fun visitForStatAST(ast: ForStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val condLabel = codeGenerator.getNextLabel()
        val bodyLabel = codeGenerator.getNextLabel()
        /**Translates the first statement in the loop before jumping to condition*/
        val stackOffset = ast.blockST.getStackOffset()
        ast.blockST.startingOffset = stackOffset
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        instrs.addAll(visit(ast.stat))
        instrs.add(BranchInstr(Condition.AL, condLabel, false))

        instrs.add(bodyLabel)
        /** Translates all the statements within the for loop body */
        ast.body.forEach { instrs.addAll(visit(it)) }
        /**Translates the change in loop variable as defined in the loop*/
        instrs.addAll(visit(ast.inc))
        /** Translates the condition after the loop body.*/
        instrs.add(condLabel)
        instrs.addAll(visit(ast.cond))
        instrs.add(CompareInstr(codeGenerator.seeLastUsedCalleeReg(), ImmediateIntOperand(1)))
        instrs.add(BranchInstr(Condition.EQ, bodyLabel, false))
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        codeGenerator.freeCalleeReg()
        return instrs
    }

    /** Translates a Action Statment AST */
    override fun visitActionStatAST(ast: ActionStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        /** Translates the expression of the statment*/
        instrs.addAll(visit(ast.expr))
        val reg = codeGenerator.seeLastUsedCalleeReg()
        val exprType: TypeAST = ast.expr.getRealType(ast.symTable)
        if (ast.expr is ArrayElemAST) {
            var memType: MemoryType? = null
            if (exprType.isBoolOrChar()) {
                memType = MemoryType.SB
            }
            instrs.add(LoadInstr(Condition.AL, memType, RegisterMode(reg), reg))
        }
        when (ast.action) {
            Action.EXIT -> {
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                instrs.add(BranchInstr(Condition.AL, Label("exit"), true))
                codeGenerator.freeAllCalleeReg()
            }
            Action.PRINT, Action.PRINTLN -> {
                /** Adds specific code for printing.*/
                when (exprType) {
                    is BaseTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        when (exprType.type) {
                            BaseType.INT -> {
                                codeGenerator.cLib.addCode(CLibrary.Call.PRINT_INT)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_INT.toString()), true))
                            }
                            BaseType.CHAR -> {
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.PUTCHAR.toString()), true))
                            }
                            BaseType.BOOL -> {
                                codeGenerator.cLib.addCode(CLibrary.Call.PRINT_BOOL)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_BOOL.toString()), true))
                            }
                            BaseType.STRING -> {
                                codeGenerator.cLib.addCode(CLibrary.Call.PRINT_STRING)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            }
                        }
                    }
                    is ArrayTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        if (exprType.type == BaseTypeAST(BaseType.CHAR)) {
                            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            codeGenerator.cLib.addCode(CLibrary.Call.PRINT_STRING)
                        } else {
                            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                            codeGenerator.cLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                        }
                    }
                    is PairTypeAST, is AnyPairTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                        codeGenerator.cLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                    }
                    is PointerTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                        codeGenerator.cLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                    }
                }
                if (ast.action == Action.PRINTLN) {
                    codeGenerator.cLib.addCode(CLibrary.Call.PRINT_LN)
                    instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_LN.toString()), true))
                }
                codeGenerator.freeCalleeReg()
            }
            Action.FREE -> {
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(codeGenerator.seeLastUsedCalleeReg())))
                val methodName = when (exprType) {
                    is ArrayTypeAST -> {
                        CLibrary.Call.FREE_ARRAY
                    }
                    is PairTypeAST -> {
                        CLibrary.Call.FREE_PAIR
                    }
                    is StructTypeAST -> {
                        CLibrary.Call.FREE_STRUCT
                    }
                    else -> {
                        CLibrary.Call.FREE_ARRAY
                    } // Should never reach here since the semantic check only allows the above
                }
                instrs.add(BranchInstr(Condition.AL, Label(methodName.toString()), true))
                codeGenerator.cLib.addCode(methodName)
                codeGenerator.freeCalleeReg()
            }
            Action.RETURN -> {
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))

                instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(ast.symTable.getFuncStackOffset())))
                instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
                codeGenerator.freeAllCalleeReg()
            }
        }
        return instrs

    }

    override fun visitVoidReturnStatAST(ast: VoidReturnStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(ast.symTable.getFuncStackOffset())))
        instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
        codeGenerator.freeAllCalleeReg()

        return instrs
    }

    /** Translates a Call Statement AST */
    override fun visitCallStatAST(ast: CallStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        /** Translates the right hand side of the assignment */
        instrs.addAll(visit(ast.rhs))

        val rhsType = ast.rhs.getRealType(ast.symTable)

        codeGenerator.freeCalleeReg()
        return instrs
    }

    /** Translates a Assign Statement AST */
    override fun visitAssignStatAST(ast: AssignStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        /** Translates the right hand side of the assignment */
        instrs.addAll(visit(ast.rhs))
        val calleeReg = codeGenerator.seeLastUsedCalleeReg()
        if (ast.rhs is StrLiterAST) {
            ast.stringLabel = codeGenerator.dataDirective.getStringLabel(ast.rhs.value)
        }

        val rhsType = ast.rhs.getRealType(ast.symTable)
        var memtype: MemoryType? = null
        if (rhsType is BaseTypeAST) {
            if (rhsType.isBoolOrChar()) {
                memtype = MemoryType.B
            }
        }

        /** Adds specific code for special RHS which require "setting up" */
        when (ast.rhs) {
            is PairElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(calleeReg), calleeReg))
            }
            is PointerElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(calleeReg), calleeReg))
            }
        }

        when (ast.lhs) {
            is IdentAST -> {
                var (correctSTScope, offset) = ast.symTable.getSTWithIdentifier(ast.lhs.name, rhsType)
                offset += ast.symTable.checkParamInFuncSymbolTable(ast.lhs.name)
                instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(Register.SP, correctSTScope.findOffsetInStack(ast.lhs.name) + offset, false), calleeReg))
            }
            is ArrayElemAST -> {
                instrs.addAll(visit(ast.lhs))
                instrs.add(StoreInstr(memtype, RegisterMode(codeGenerator.seeLastUsedCalleeReg()), calleeReg))
                codeGenerator.freeCalleeReg()
            }
            is PairElemAST -> {
                instrs.addAll(visit(ast.lhs))
                instrs.add(StoreInstr(memtype, RegisterMode(codeGenerator.seeLastUsedCalleeReg()), calleeReg))
                codeGenerator.freeCalleeReg()
            }
            is PointerElemAST -> {
                instrs.addAll(visit(ast.lhs))
                instrs.add(StoreInstr(memtype, RegisterMode(codeGenerator.seeLastUsedCalleeReg()), calleeReg))
                codeGenerator.freeCalleeReg()
            }
            is StructAccessAST -> {
                val stackOffset = ast.symTable.findOffsetInStack(ast.lhs.structIdent.name)
                val structReg = codeGenerator.getNextFreeCalleeReg()
                instrs.add(LoadInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, stackOffset, false), structReg))
                val accessOffset = ast.lhs.structDeclare.getOffsetInStruct(ast.lhs.fieldIdent)
                instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(structReg, accessOffset, false), calleeReg))
                codeGenerator.freeCalleeReg()
            }
        }
        codeGenerator.freeCalleeReg()
        return instrs
    }

    /** Translates a Declare Statement AST */
    override fun visitDeclareStatAST(ast: DeclareStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        /** Translates the right hand side of the declare */
        if (ast.rhs !is StructAssignRhsAST) {
            instrs.addAll(visit(ast.rhs))
        }
        if (ast.rhs is StrLiterAST) {
            ast.stringLabel = codeGenerator.dataDirective.getStringLabel(ast.rhs.value)
        }
        ast.symTable.decreaseOffset(ast.ident, ast.rhs.getRealType(ast.symTable))
        var memtype: MemoryType? = null
        when (ast.type) {
            is BaseTypeAST -> {
                if (ast.type.isBoolOrChar()) {
                    memtype = MemoryType.B
                }
            }
            is ArrayTypeAST -> {
                // Intentionally Left Blank
            }
            is PairTypeAST -> {
                /** Adds a load instruction provided the RHS is not some specific type */
                if (ast.rhs !is NewPairRhsAST && ast.rhs !is ArrayElemAST && ast.rhs !is IdentAST &&
                        ast.rhs !is NullPairLiterAST && ast.rhs !is CallRhsAST && ast.rhs !is PairElemAST) {
                    instrs.add(LoadInstr(Condition.AL, null, RegisterMode(codeGenerator.seeLastUsedCalleeReg()), codeGenerator.seeLastUsedCalleeReg()))
                }
            }
            is StructTypeAST -> {
                val structType = ast.type as StructTypeAST
                val structInST = ast.symTable.lookupAll(structType.ident.name)

                /** Checks that the struct we are looking for is in the symbol table*/
                if (structInST.isEmpty || structInST.get() !is StructDeclareStatAST) {
                    throw RuntimeException("Struct not in symbol table during Code gen")
                }
                val structDeclareAST = structInST.get() as StructDeclareStatAST
                /** Mallocs space for all elements in the struct*/
                instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(structDeclareAST.totalSizeOfFields), Register.R0))
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
                val stackReg = codeGenerator.getNextFreeCalleeReg()
                instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))
                instrs.addAll(visit(ast.rhs))
                instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(Register.SP, ast.symTable.findOffsetInStack(ast.ident.name), false), codeGenerator.seeLastUsedCalleeReg()))
                codeGenerator.freeCalleeReg()
                return instrs

            }
        }
        val offset = ast.symTable.offsetSize
        when (ast.rhs) {
            is PairElemAST -> {
                instrs.add(LoadInstr(Condition.AL, memtype, RegisterMode(codeGenerator.seeLastUsedCalleeReg()), codeGenerator.seeLastUsedCalleeReg()))
            }
            is ArrayElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(codeGenerator.seeLastUsedCalleeReg()), codeGenerator.seeLastUsedCalleeReg()))
            }
        }
        instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(Register.SP, offset, false), codeGenerator.seeLastUsedCalleeReg()))
        codeGenerator.freeCalleeReg()

        return instrs
    }

    /** Translates a Read Statement AST */
    override fun visitReadStatAST(ast: ReadStatAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        when (ast.expr) {
            is IdentAST -> {
                val (correctSTScope, offset) = ast.symTable.getSTWithIdentifier(ast.expr.name, (ast.exprType as BaseTypeAST))
                instrs.add(AddInstr(Condition.AL, Register.R4, Register.SP, ImmediateIntOperand(correctSTScope.findOffsetInStack(ast.expr.name) + offset)))
            }
            is ArrayElemAST -> {
                // Intentionally Left Blank
            }
            is PairElemAST -> {
                /** Translates the expression */
                instrs.addAll(visit(ast.expr))
            }
        }
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R4)))

        /** Adds specific calls to read library functions */
        when ((ast.exprType as BaseTypeAST).type) {
            BaseType.INT -> {
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_INT.toString()), true))
                codeGenerator.cLib.addCode(CLibrary.Call.READ_INT)
            }
            BaseType.CHAR -> {
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_CHAR.toString()), true))
                codeGenerator.cLib.addCode(CLibrary.Call.READ_CHAR)
            }
            else -> throw RuntimeException("Read can only be used for int or char, semantic check failed")
        }
        return instrs
    }

    /** Translates a Skip Statement AST. Requires no code generation */
    override fun visitSkipStatAST(ast: SkipStatAST): List<Instruction> {
        return emptyList()
    }

    /** Translates a Begin .. End block of statements */
    override fun visitMultiStatAST(ast: MultiStatAST): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        ast.stats.forEach { instr.addAll(visit(it)) }
        return instr
    }

    /** Translates a New Pair statement */
    override fun visitNewPairRhsAST(ast: NewPairRhsAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        var memtype: MemoryType? = null
        val spaceForTwoPointers = 2 * pointerOffset
        /** Mallocs space for two pointers to the first and second elements */
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(spaceForTwoPointers), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        val stackReg = codeGenerator.getNextFreeCalleeReg()
        instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        /** Malloc first element */
        instrs.addAll(visit(ast.fst))
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(SymbolTable.getBytesOfType(ast.firstType)), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (ast.firstType.isBoolOrChar()) {
            memtype = MemoryType.B
        }
        instrs.add(StoreInstr(memtype, RegisterMode(Register.R0), codeGenerator.seeLastUsedCalleeReg()))
        codeGenerator.freeCalleeReg()
        instrs.add(StoreInstr(null, RegisterMode(stackReg), Register.R0))

        /** Malloc second element */
        instrs.addAll(visit(ast.snd))
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(SymbolTable.getBytesOfType(ast.secondType)), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (ast.secondType.isBoolOrChar()) {
            memtype = MemoryType.B
        }
        instrs.add(StoreInstr(memtype, RegisterMode(Register.R0), codeGenerator.seeLastUsedCalleeReg()))
        codeGenerator.freeCalleeReg()
        instrs.add(StoreInstr(null, RegisterAddrWithOffsetMode(stackReg, pointerOffset, false), Register.R0))

        return instrs

    }

    /** Translates a Call RHS AST */
    override fun visitCallRhsAST(ast: CallRhsAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        /** Translates arguements in reverse order */
        var totalBytes = 0
        val argTypesReversed = ast.argTypes.reversed()
        val negativeCallStackOffset = -1
        for ((index, arg) in ast.argList.reversed().withIndex()) {
            var memType: MemoryType? = null
            instrs.addAll(visit(arg))
            val reg = codeGenerator.seeLastUsedCalleeReg()
            val bytes = getBytesOfType(argTypesReversed[index])
            totalBytes += bytes
            ast.symTable.callOffset = totalBytes
            if (argTypesReversed[index].isBoolOrChar()) {
                memType = MemoryType.B
            }
            if (arg is ArrayElemAST) {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg), reg))
            }
            instrs.add(StoreInstr(memType, RegisterAddrWithOffsetMode(Register.SP, negativeCallStackOffset * bytes, true), reg))
            codeGenerator.freeCalleeReg()
        }
        ast.symTable.callOffset = 0

        val funcLabel = FunctionLabel(ast.ident.name)
        instrs.add(BranchInstr(Condition.AL, funcLabel, true))
        instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(totalBytes), false))
        instrs.add(MoveInstr(Condition.AL, codeGenerator.getNextFreeCalleeReg(), RegisterOperand(Register.R0)))
        return instrs
    }

    /** Translates a Binary operator */
    override fun visitBinOpExprAST(ast: BinOpExprAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        var reg1: Register
        var reg2: Register

        // This boolean value is for register allocation optimisation.
        // Division and modulus are ignored because of their complexity in using registers.
        // Pointer operation is ignored because its reverse is hard to accommodate
        // the shift operation in pointer arithmetics.
        val reverse = ast.expr1.weight() <= ast.expr2.weight()
                && ast.binOp != IntBinOp.DIV && ast.binOp != IntBinOp.MOD // Ignore division and modulus
                && !ast.pointerOp // Ignore pointer operation
        if (!reverse) {
            instrs.addAll(visit(ast.expr1))
            reg1 = codeGenerator.seeLastUsedCalleeReg()
            instrs.addAll(visit(ast.expr2))
            reg2 = codeGenerator.seeLastUsedCalleeReg()
        } else {
            instrs.addAll(visit(ast.expr2))
            reg1 = codeGenerator.seeLastUsedCalleeReg()
            instrs.addAll(visit(ast.expr1))
            reg2 = codeGenerator.seeLastUsedCalleeReg()
        }

        /** Decides whether to use accumulator and sets appropriate registers when required */
        var useAccumulator = false
        if (reg1 == Register.NONE || reg1 == Register.R10) {
            useAccumulator = true
            reg1 = Register.R10
            reg2 = Register.R11
        }

        /** Add appropriate instructions depending on instruction typeAdd opUse different instructions for accumulator when required */
        when (ast.binOp) {
            IntBinOp.PLUS -> {
                // This operator is commutative so its reverse is the same. No need for a reverse case.
                if (!useAccumulator) {
                    if (!ast.pointerOp) {
                        instrs.add(AddInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                    } else {
                        instrs.add(AddInstr(Condition.AL, reg1, reg1, RegShiftOffsetOperand(reg2, ShiftType.LSL, ast.shiftOffset), true))
                    }
                } else {
                    instrs.add(PopInstr(Register.R11))
                    if (!ast.pointerOp) {
                        instrs.add(AddInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                    } else {
                        instrs.add(AddInstr(Condition.AL, reg1, reg2, RegShiftOffsetOperand(reg1, ShiftType.LSL, ast.shiftOffset), true))
                    }
                }
                instrs.add(BranchInstr(Condition.VS, RuntimeErrors.throwOverflowErrorLabel, true))
                codeGenerator.runtimeErrors.addOverflowError()
            }
            IntBinOp.MINUS -> {
                // An RSB instruction is used for the reverse minus.
                if (!reverse) {
                    if (!useAccumulator) {
                        if (!ast.pointerOp) {
                            instrs.add(SubInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                        } else {
                            instrs.add(SubInstr(Condition.AL, reg1, reg1, RegShiftOffsetOperand(reg2, ShiftType.LSL, ast.shiftOffset), true))
                        }
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        if (!ast.pointerOp) {
                            instrs.add(SubInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                        } else {
                            instrs.add(SubInstr(Condition.AL, reg1, reg2, RegShiftOffsetOperand(reg1, ShiftType.LSL, ast.shiftOffset), true))
                        }
                    }
                } else {
                    if (!useAccumulator) {
                        if (!ast.pointerOp) {
                            instrs.add(ReverseSubInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                        } else {
                            instrs.add(ReverseSubInstr(Condition.AL, reg1, reg1, RegShiftOffsetOperand(reg2, ShiftType.LSL, ast.shiftOffset), true))
                        }
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        if (!ast.pointerOp) {
                            instrs.add(ReverseSubInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                        } else {
                            instrs.add(ReverseSubInstr(Condition.AL, reg1, reg2, RegShiftOffsetOperand(reg1, ShiftType.LSL, ast.shiftOffset), true))
                        }
                    }

                }
                instrs.add(BranchInstr(Condition.VS, RuntimeErrors.throwOverflowErrorLabel, true))
                codeGenerator.runtimeErrors.addOverflowError()
            }
            IntBinOp.MULT -> {
                // This operator is commutative so its reverse is the same. No need for a reverse case.
                val shiftAmount = 31
                if (!useAccumulator) {
                    instrs.add(MultInstr(Condition.AL, reg1, reg2, reg1, reg2))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MultInstr(Condition.AL, reg1, reg2, reg2, reg1))
                }
                instrs.add(CompareInstr(reg2, RegShiftOffsetOperand(reg1, ShiftType.ASR, shiftAmount)))
                instrs.add(BranchInstr(Condition.NE, RuntimeErrors.throwOverflowErrorLabel, true))
                codeGenerator.runtimeErrors.addOverflowError()
            }
            IntBinOp.DIV -> {
                val assemblyDivideFunName = "__aeabi_idiv"
                if (!useAccumulator) {
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg2)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg1)))
                }
                instrs.add(BranchInstr(Condition.AL, RuntimeErrors.divideZeroCheckLabel, true))
                codeGenerator.runtimeErrors.addDivideByZeroCheck()
                instrs.add(BranchInstr(Condition.AL, Label(assemblyDivideFunName), true))
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R0)))
            }
            IntBinOp.MOD -> {
                val assemblyDivModFunName = "__aeabi_idivmod"
                if (!useAccumulator) {
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg2)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg1)))
                }
                instrs.add(BranchInstr(Condition.AL, RuntimeErrors.divideZeroCheckLabel, true))
                codeGenerator.runtimeErrors.addDivideByZeroCheck()
                instrs.add(BranchInstr(Condition.AL, Label(assemblyDivModFunName), true))
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R1)))
            }
            CmpBinOp.EQ -> {
                // This operator is commutative so its reverse is the same. No need for a reverse case.
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.EQ, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.NE, reg1, ImmediateBoolOperand(false)))
            }

            CmpBinOp.NEQ -> {
                // This operator is commutative so its reverse is the same. No need for a reverse case.
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.NE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.EQ, reg1, ImmediateBoolOperand(false)))
            }
            CmpBinOp.LTE -> {
                if (!reverse) {
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(false)))
                } else {
                    // GTE is used for the reverse LTE.
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(false)))
                }

            }
            CmpBinOp.LT -> {
                if (!reverse) {
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(false)))
                } else {
                    // GT is used for the reverse LT.
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(false)))
                }
            }
            CmpBinOp.GTE -> {
                if (!reverse) {
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(false)))
                } else {
                    // LTE is used for the reverse GTE.
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(false)))
                }
            }
            CmpBinOp.GT -> {
                if (!reverse) {
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(false)))
                } else {
                    // LT is used for the reverse GT.
                    if (!useAccumulator) {
                        instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                    } else {
                        instrs.add(PopInstr(Register.R11))
                        instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                    }
                    instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(true)))
                    instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(false)))
                }

            }

            BoolBinOp.AND -> {
                // This operator is commutative so its reverse is the same. No need for a reverse case.
                if (!useAccumulator) {
                    instrs.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))

                }
            }
            BoolBinOp.OR -> {
                // This operator is commutative so its reverse is the same. No need for a reverse case.
                if (!useAccumulator) {
                    instrs.add(OrInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(OrInstrType(Condition.AL, reg1, reg2, RegisterOperand(reg1)))
                }
            }
        }
        if (!useAccumulator) {
            codeGenerator.freeCalleeReg()
        }
        return instrs
    }

    /** Translates a Unary Operator AST */
    override fun visitUnOpExprAST(ast: UnOpExprAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(visit(ast.expr))
        val reg1 = codeGenerator.seeLastUsedCalleeReg()
        /** Add appropriate instructions depending on operation type */
        when (ast.unOp) {
            UnOp.NOT -> {
                instrs.add(XorInstrType(Condition.AL, reg1, reg1, ImmediateIntOperand(1)))
            }
            UnOp.MINUS -> {
                instrs.add(ReverseSubInstr(Condition.AL, reg1, reg1, ImmediateIntOperand(0), true))
                instrs.add(BranchInstr(Condition.VS, RuntimeErrors.throwOverflowErrorLabel, true))
                codeGenerator.runtimeErrors.addOverflowError()
            }
            UnOp.LEN -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(Register.SP), reg1))
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg1), reg1))
            }
            UnOp.ORD -> {
                // Intentionally Left Blank
            }
            UnOp.CHR -> {
                // Intentionally Left Blank
            }
            UnOp.REF -> {
                when (ast.expr) {
                    is IdentAST -> {
                        val ident = ast.expr
                        /** Computes offset to push down the stack pointer */
                        var stackOffset = ident.symTable.findOffsetInStack(ident.name)
                        stackOffset += ident.symTable.checkParamInFuncSymbolTable(ident.name) + ident.symTable.callOffset
                        instrs.add(AddInstr(Condition.AL, reg1, Register.SP, ImmediateIntOperand(stackOffset), false))
                    }
                    is ArrayElemAST -> {
                        // Intentionally leave blank
                    }
                }
                // Load the address of the variable to register.
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(reg1)))
            }
            UnOp.DEREF -> {
                /** Translates the expression */
                if (ast.expr is ArrayElemAST) {
                    instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg1), reg1))
                }
                // Perform action similar to PairElem
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                instrs.add(BranchInstr(Condition.AL, RuntimeErrors.nullReferenceLabel, true))
                codeGenerator.runtimeErrors.addNullReferenceCheck()
                var memType : MemoryType? = null
                if ((ast.expr.getRealType(ast.symTable) as PointerTypeAST).type.isBoolOrChar()) {
                    memType = MemoryType.SB
                }
                // Load real value from memory.
                instrs.add(LoadInstr(Condition.AL, memType, RegisterMode(reg1), reg1))

            }
        }
        return instrs
    }

    /** Translates an Array element AST (i.e. array[0]) */
    override fun visitArrayElemAST(ast: ArrayElemAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val stackReg = codeGenerator.getNextFreeCalleeReg()

        /** Computes offset to push down the stack pointer */
        var stackOffset = ast.symTable.findOffsetInStack(ast.ident.name)
        stackOffset += ast.symTable.checkParamInFuncSymbolTable(ast.ident.name) + ast.symTable.callOffset
        instrs.add(AddInstr(Condition.AL, stackReg, Register.SP, ImmediateIntOperand(stackOffset), false))

        /** Translates each indices of the array element (multi-dimensional arrays) */
        ast.indices.forEach {
            instrs.addAll(visit(it))
            /** Checks the access is in bounds of the array */
            instrs.add(LoadInstr(Condition.AL, null, RegisterMode(stackReg), stackReg))
            instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(codeGenerator.seeLastUsedCalleeReg())))
            instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(stackReg)))
            instrs.add(BranchInstr(Condition.AL, RuntimeErrors.checkArrayBoundsLabel, true))
            codeGenerator.runtimeErrors.addArrayBoundsCheck()

            instrs.add(AddInstr(Condition.AL, stackReg, stackReg, ImmediateIntOperand(pointerOffset), false))
            val identType = ast.ident.getRealType(ast.symTable)
            if ((identType is ArrayTypeAST) && identType.type.isBoolOrChar()) {
                instrs.add(AddInstr(Condition.AL, stackReg, stackReg, RegisterOperand(codeGenerator.seeLastUsedCalleeReg()), false))
            } else {
                val multiplyByFour = 2
                instrs.add(AddInstr(Condition.AL, stackReg, stackReg, RegShiftOffsetOperand(codeGenerator.seeLastUsedCalleeReg(), ShiftType.LSL, multiplyByFour), false))
            }
            codeGenerator.freeCalleeReg()
        }
        return instrs

    }

    /** Translates a Pair Elem AST */
    override fun visitPairElemAST(ast: PairElemAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        /** Translates the expression */
        instrs.addAll(visit(ast.expr))
        val reg = codeGenerator.seeLastUsedCalleeReg()
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
        instrs.add(BranchInstr(Condition.AL, RuntimeErrors.nullReferenceLabel, true))
        codeGenerator.runtimeErrors.addNullReferenceCheck()
        if (ast.choice == PairChoice.FST) {
            instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg), reg))
        } else {
            instrs.add(LoadInstr(Condition.AL, null, RegisterAddrWithOffsetMode(reg, pointerOffset, false), reg))
        }
        return instrs
    }

    override fun visitPointerElemAST(ast: PointerElemAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        /** Translates the expression */
        instrs.addAll(visit(ast.ident))
        // Perform action similar to PairElem
        val reg = codeGenerator.seeLastUsedCalleeReg()
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
        instrs.add(BranchInstr(Condition.AL, RuntimeErrors.nullReferenceLabel, true))
        codeGenerator.runtimeErrors.addNullReferenceCheck()
//        instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg), reg))

        return instrs
    }

    /** Translates an Identifier AST */
    override fun visitIdentAST(ast: IdentAST): List<Instruction> {
        var offset = ast.symTable.findOffsetInStack(ast.name)
        var memType: MemoryType? = null
        val type = ast.getRealType(ast.symTable)
        if (type.isBoolOrChar()) {
            memType = MemoryType.SB
        }
        offset += ast.symTable.checkParamInFuncSymbolTable(ast.name) + ast.symTable.callOffset
        return listOf(LoadInstr(Condition.AL, memType, RegisterAddrWithOffsetMode(Register.SP, offset, false), codeGenerator.getNextFreeCalleeReg()))
    }

    /** Translates a Boolean IntAST */
    override fun visitIntLiterAST(ast: IntLiterAST): List<Instruction> {
        var reg = codeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {
            // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateIntMode(ast.value), reg)
        return instrs
    }

    /** Translates a Boolean Literal AST */
    override fun visitBoolLiterAST(ast: BoolLiterAST): List<Instruction> {
        var reg = codeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {
            // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateBoolOperand(ast.value))
        return instrs
    }

    /** Translates a String Literal AST */
    override fun visitStrLiterAST(ast: StrLiterAST): List<Instruction> {
        var reg = codeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {
            // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        val strLabel = codeGenerator.dataDirective.addStringLabel(ast.value)
        instrs += LoadInstr(Condition.AL, null, ImmediateLabelMode(strLabel), reg)
        return instrs
    }

    /** Translates a Char Literal AST */
    override fun visitCharLiterAST(ast: CharLiterAST): List<Instruction> {
        var reg = codeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {
            // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateCharOperand(ast.value))
        return instrs
    }

    /** Translates a Null Literal AST used inside pairs */
    override fun visitNullPairLiterAST(ast: NullPairLiterAST): List<Instruction> {
        var reg = codeGenerator.getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {
            // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateIntMode(0), reg)
        return instrs
    }

    /** Translates an Array Literal AST */
    override fun visitArrayLiterAST(ast: ArrayLiterAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val elemSize = SymbolTable.getBytesOfType((ast.arrayType as ArrayTypeAST).type)

        /** Loads the size to malloc, which is length + 4 for storing the size */
        val sizeOfInt = 4
        instrs.add(LoadInstr(Condition.AL, null,
                ImmediateIntMode(elemSize * ast.values.size + sizeOfInt), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label("malloc"), true))
        val stackReg = codeGenerator.getNextFreeCalleeReg()
        instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        /** Adds all the elements */
        var memType: MemoryType? = null
        for ((index, expr) in ast.values.withIndex()) {
            if (expr is IdentAST) {
                expr.symTable = ast.symTable
            }
            instrs.addAll(visit(expr))
            if ((expr is CharLiterAST) || (expr is BoolLiterAST)) {
                memType = MemoryType.B
            }
            instrs.add(StoreInstr(memType,
                    RegisterAddrWithOffsetMode(stackReg, sizeOfInt + (index * elemSize), false), codeGenerator.seeLastUsedCalleeReg()))
            codeGenerator.freeCalleeReg()
        }

        /** Adds the size of the array*/
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(ast.values.size), codeGenerator.getNextFreeCalleeReg()))
        instrs.add(StoreInstr(null,
                RegisterMode(stackReg), codeGenerator.seeLastUsedCalleeReg()))
        codeGenerator.freeCalleeReg()
        return instrs
    }

    /** Translates a Type AST. Requires no code generation  */
    override fun visitTypeAST(ast: TypeAST): List<Instruction> {
        return emptyList()
    }

    /** Translates a Struct Declare AST. Requires no code generation */
    override fun visitStructDeclareAST(ast: StructDeclareStatAST): List<Instruction> {
        return emptyList()
    }

    override fun visitStructAssignAST(ast: StructAssignRhsAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val symbolTable = ast.symTable
        var memtype: MemoryType? = null
        val stackReg = codeGenerator.seeLastUsedCalleeReg()
        var fieldOffset = 0
        for (assign in ast.assignments) {
            instrs.addAll(visit(assign))
            val assignType = assign.getRealType(symbolTable)
//            instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(SymbolTable.getBytesOfType(assignType)), Register.R0))
//            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
            if (assignType.isBoolOrChar()) {
                memtype = MemoryType.B
            }
            instrs.add(StoreInstr(memtype, RegisterAddrWithOffsetMode(stackReg, fieldOffset, false), codeGenerator.seeLastUsedCalleeReg()))
            codeGenerator.freeCalleeReg()
            fieldOffset += getBytesOfType(assign.getRealType(symbolTable))
//            instrs.add(StoreInstr(null, RegisterMode(stackReg), Register.R0))

        }
        return instrs
    }

    override fun visitStructAccessAST(ast: StructAccessAST): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val memtype: MemoryType? = null
        var stackOffset = ast.symTable.findOffsetInStack(ast.structIdent.name)
        stackOffset += ast.symTable.checkParamInFuncSymbolTable(ast.structIdent.name)
        val resultReg = codeGenerator.getNextFreeCalleeReg()
        val structReg = codeGenerator.getNextFreeCalleeReg()
        instrs.add(LoadInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, stackOffset, false), structReg))
        val accessOffset = ast.structDeclare.getOffsetInStruct(ast.fieldIdent)
        instrs.add(LoadInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(structReg, accessOffset, false), resultReg))
        codeGenerator.freeCalleeReg()
        return instrs
    }
}
