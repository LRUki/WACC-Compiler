package wacc.frontend.ast.statement.block

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
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
    lateinit var blockST: SymbolTable

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!cond.check(table)) {
            return false
        }
        val condType = cond.getRealType(table)
        if (condType != TypeInstance.boolTypeInstance) {
            semanticError("While condition must evaluate to a BOOL, but was actually $condType", ctx)
            return false
        }
        blockST = SymbolTable(table)
        body.forEach {
            if (!it.check(blockST)) {
                return false
            }
        }
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitWhileStatAST(this)
    }


}