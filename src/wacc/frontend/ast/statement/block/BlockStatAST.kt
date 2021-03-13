package wacc.frontend.ast.statement.block

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.statement.StatAST

/**
 * AST node to represent a BEGIN .. END block statement
 *
 * @property body List of statements inside the block
 */
class BlockStatAST(val body: List<StatAST>) : StatAST {
    lateinit var symTable: SymbolTable
    override fun check(table: SymbolTable): Boolean {
        symTable = SymbolTable(table)
        body.forEach {
            if (!it.check(symTable)) {
                return false
            }
        }
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitBlockStatAST(this)
    }

    override fun weight(): Int {
        TODO("Not yet implemented")
    }

}