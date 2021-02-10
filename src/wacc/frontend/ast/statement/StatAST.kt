package wacc.frontend.ast.statement

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST


interface StatAST : AST

class SkipStatAST : StatAST

/**
Used to temporary store multiple statements.
 */
class MultiStatAST(val stats: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        val blockST = SymbolTable(table)
        stats.forEach { it.check(blockST) }
        return true
    }
}
