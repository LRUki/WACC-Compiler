package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator.CLib
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.Instruction
import wacc.backend.translate.enums.Condition
import wacc.backend.translate.enums.MemoryType
import wacc.backend.translate.enums.Register
import wacc.backend.translate.instrs.BranchInstr
import wacc.backend.translate.instrs.Label
import wacc.backend.translate.instrs.LoadInstr
import wacc.backend.translate.instrs.MoveInstr
import wacc.backend.translate.utils.CLibrary
import wacc.backend.translate.utils.RegisterAddr
import wacc.backend.translate.utils.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.*
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Action Statement
 *
 * @property action An action declared in the ACTION enum
 * @property expr Expression to perform action on
 */
class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr.check(table)) {
            return false
        }
        val exprType = expr.getRealType(table)
        when (action) {
            Action.FREE -> {
                if (exprType is ArrayTypeAST || exprType is PairTypeAST) {
                    return true
                }
                semanticError("Expected type ARRAY or PAIR, Actual type $exprType", ctx)
            }
            Action.RETURN -> {
                val closestFunc = table.lookupFirstFunc()
                if (closestFunc.isEmpty) {
                    semanticError("A return token is outside of a function scope", ctx)
                    return false
                }
                val returnType = (closestFunc.get()).type
                if (returnType != exprType) {
                    semanticError("Expected type $returnType, Actual type $exprType", ctx)
                    return false
                }
                return true
            }
            Action.EXIT -> {
                if (exprType == TypeInstance.intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $exprType", ctx)
            }
            Action.PRINT, Action.PRINTLN -> {
                return expr.check(table)
            }
        }
        return false
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        instr.addAll(expr.translate())
        val reg = seeLastUsedCalleeReg()
        val exprType = expr.getRealType(symTable)
        if (expr is ArrayElemAST) {
            var memType : MemoryType? = null
            if (exprType == BaseTypeAST(BaseType.BOOL) || exprType == BaseTypeAST(BaseType.CHAR)) {
                memType = MemoryType.SB
            }
            instr.add(LoadInstr(Condition.AL, memType, RegisterAddr(reg), reg))
        }
        when (action) {
            Action.EXIT -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                instr.add(BranchInstr(Condition.AL, Label("exit"), true))
            }
            Action.PRINT, Action.PRINTLN -> {
                when (exprType) {
                    is BaseTypeAST -> {
                        instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        when (exprType.type) {
                            BaseType.INT -> {
                                CLib.addCode(CLibrary.Call.PRINT_INT)
                                instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_INT.toString()), true))
                            }
                            BaseType.CHAR -> {
                                instr.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.PUTCHAR.toString()), true))
                            }
                            BaseType.BOOL -> {
                                CLib.addCode(CLibrary.Call.PRINT_BOOL)
                                instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_BOOL.toString()), true))
                            }
                            BaseType.STRING -> {
                                CLib.addCode(CLibrary.Call.PRINT_STRING)
                                instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            }
                        }
                    }
                    is ArrayTypeAST -> {
                        instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        if (exprType.type == BaseTypeAST(BaseType.CHAR)) {
                            instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            CLib.addCode(CLibrary.Call.PRINT_STRING)
                        } else {
                            instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                            CLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                        }
                    }
                    is PairTypeAST, is AnyPairTypeAST -> {
                        instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                        CLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                    }
                }
                if (action == Action.PRINTLN) {
                    CLib.addCode(CLibrary.Call.PRINT_LN)
                    instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_LN.toString()), true))
                }
                freeCalleeReg()
            }
            Action.FREE -> {
//                val stackOffset = symTable.findOffsetInStack((expr as IdentAST).name)
//                instr.add(LoadInstr(getNextFreeCalleeReg(), null, RegisterAddrWithOffset(Register.SP, stackOffset, false), Condition.AL))
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
                instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.FREE_PAIR.toString()), true))
                CLib.addCode(CLibrary.Call.FREE_PAIR)
            }
            Action.RETURN -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
            }
        }
        return instr
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}
