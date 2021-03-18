package wacc.frontend.ast.statement.block
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a FOR DO DONE statement
 *
 * @property cond Boolean expression for the condition
 * @property body List of statements to be executed when cond == true
 */
class ForStatAST(val stat: StatAST, val cond: ExprAST, val inc: StatAST, val body: List<StatAST>) : StatAST, AbstractAST() {
    lateinit var blockST: SymbolTable

    override fun check(table: SymbolTable): Boolean {
        blockST = SymbolTable(table)
        symTable = table
        if(!stat.check(blockST) || !inc.check(blockST)){
            return false
        }
        if (!cond.check(blockST)) {
            return false
        }
        val condType = cond.getRealType(blockST)
        if (condType != TypeInstance.boolTypeInstance) {
            semanticError("For condition must evaluate to a BOOL, but was actually $condType", ctx)
            return false
        }
        body.forEach {
            if (!it.check(blockST)) {
                return false
            }
        }
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitForStatAST(this)
    }

}