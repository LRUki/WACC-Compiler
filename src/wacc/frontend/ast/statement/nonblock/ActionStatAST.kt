package wacc.frontend.ast.statement.nonblock

import org.antlr.v4.codegen.CodeGenerator
import wacc.backend.CodeGenerator.CLib
import wacc.backend.CodeGenerator.getLastUsedCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.Label
import wacc.backend.instruction.instrs.LoadInstr
import wacc.backend.instruction.instrs.MoveInstr
import wacc.backend.instruction.utils.CLibrary
import wacc.backend.instruction.utils.RegisterAddr
import wacc.backend.instruction.utils.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
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
        if (!expr.check(table)) {return false}
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
        val reg = getLastUsedCalleeReg()
        val exprType = expr.getRealType(symTable)
        when (action) {
            Action.EXIT -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                instr.add(BranchInstr(Condition.AL, Label("exit"), true))
            }
            Action.PRINT, Action.PRINTLN -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                when (exprType) {
                    is BaseTypeAST -> {
                        when(exprType.type) {
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
                        TODO()}
                    is PairTypeAST -> {
                        TODO()}
                }
                if (action == Action.PRINTLN) {
                    CLib.addCode(CLibrary.Call.PRINT_LN)
                    instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_LN.toString()), true))
                }
            }
        }
        return instr
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}
