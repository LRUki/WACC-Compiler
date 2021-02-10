package wacc.frontend.ast.statement.block

import wacc.frontend.SymbolTable
import wacc.frontend.ast.statement.StatAST

class BlockStatAST(val body: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        val blockST = SymbolTable(table)
        body.forEach { it.check(blockST) }
        return true
    }
}