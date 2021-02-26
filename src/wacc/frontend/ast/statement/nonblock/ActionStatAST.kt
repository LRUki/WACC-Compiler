package wacc.frontend.ast.statement.nonblock

import org.antlr.v4.codegen.CodeGenerator
import wacc.backend.CodeGenerator.getLastUsedCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.Label
import wacc.backend.instruction.instrs.LoadInstr
import wacc.backend.instruction.instrs.MoveInstr
import wacc.backend.instruction.utils.RegisterAddr
import wacc.backend.instruction.utils.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.PairTypeAST
import wacc.frontend.ast.type.TypeInstance
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
        when (action) {
            Action.EXIT -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
                instr.add(BranchInstr(Condition.AL, Label("exit"), true))
            }
        }
        return instr
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}
