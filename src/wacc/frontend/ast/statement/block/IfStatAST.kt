package wacc.frontend.ast.statement.block

import wacc.backend.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a IF THEN ELSE FI statement
 *
 * @property cond Boolean expression for the condition
 * @property thenBody List of statements to be executed when cond == true
 * @property elseBody List of statements to be executed when cond == false
 */
class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        //cond is bool
        cond.check(table)
        val condType = cond.getRealType(table)
        if (condType != TypeInstance.boolTypeInstance) {
            semanticError("If condition must evaluate to a BOOL, but was actually $condType", ctx)
        }

        val thenST = SymbolTable(table)
        thenBody.forEach { it.check(thenST) }

        val elseST = SymbolTable(table)
        elseBody.forEach { it.check(elseST) }

        return true
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}