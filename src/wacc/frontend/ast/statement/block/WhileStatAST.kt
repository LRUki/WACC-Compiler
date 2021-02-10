package wacc.frontend.ast.statement.block

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a WHILE DO DONE statement
 *
 * @property cond Boolean expression for the condition
 * @property body List of statements to be executed when cond == true
 */
class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        cond.check(table)
        val condType = cond.getRealType(table)
        if (!condType.equals(TypeInstance.boolTypeInstance)) {
            semanticError("If condition must evaluate to a BOOL, but was actually $condType", ctx)
        }
        val blockST = SymbolTable(table)
        body.forEach { it.check(blockST) }
        return true
    }
}