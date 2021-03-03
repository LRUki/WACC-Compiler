package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator.CLib
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.BranchInstr
import wacc.backend.translate.instruction.Label
import wacc.backend.translate.instruction.LoadInstr
import wacc.backend.translate.instruction.MoveInstr
import wacc.backend.translate.CLibrary
import wacc.backend.translate.instruction.instructionpart.RegisterMode
import wacc.backend.translate.instruction.instructionpart.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
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
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(expr.translate())
        val reg = seeLastUsedCalleeReg()
        val exprType = expr.getRealType(symTable)
        if (expr is ArrayElemAST) {
            var memType: MemoryType? = null
            if (exprType == BaseTypeAST(BaseType.BOOL) || exprType == BaseTypeAST(BaseType.CHAR)) {
                memType = MemoryType.SB
            }
            instrs.add(LoadInstr(Condition.AL, memType, RegisterMode(reg), reg))
        }
        when (action) {
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
                                CLib.addCode(CLibrary.Call.PRINT_INT)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_INT.toString()), true))
                            }
                            BaseType.CHAR -> {
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.PUTCHAR.toString()), true))
                            }
                            BaseType.BOOL -> {
                                CLib.addCode(CLibrary.Call.PRINT_BOOL)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_BOOL.toString()), true))
                            }
                            BaseType.STRING -> {
                                CLib.addCode(CLibrary.Call.PRINT_STRING)
                                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            }
                        }
                    }
                    is ArrayTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        if (exprType.type == BaseTypeAST(BaseType.CHAR)) {
                            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true))
                            CLib.addCode(CLibrary.Call.PRINT_STRING)
                        } else {
                            instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                            CLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                        }
                    }
                    is PairTypeAST, is AnyPairTypeAST -> {
                        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_REFERENCE.toString()), true))
                        CLib.addCode(CLibrary.Call.PRINT_REFERENCE)
                    }
                }
                if (action == Action.PRINTLN) {
                    CLib.addCode(CLibrary.Call.PRINT_LN)
                    instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_LN.toString()), true))
                }
                freeCalleeReg()
            }
            Action.FREE -> {
//                val stackOffset = symTable.findOffsetInStack((expr as IdentAST).name)
//                instrs.add(LoadInstr(getNextFreeCalleeReg(), null, RegisterAddrWithOffset(Register.SP, stackOffset, false), Condition.AL))
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.FREE_PAIR.toString()), true))
                CLib.addCode(CLibrary.Call.FREE_PAIR)
            }
            Action.RETURN -> {
                instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
            }
        }
        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitActionStatAST(this)
    }

}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}
