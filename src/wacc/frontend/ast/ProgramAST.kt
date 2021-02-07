package wacc.frontend.ast

import wacc.frontend.SymbolTable
import wacc.frontend.ast.function.FuncAST

class ProgramAST(val funcList: List<FuncAST>, val stats: List<StatAST>) : AST {
    override fun check(table: SymbolTable): Boolean {
        funcList.forEach { it.check(table) }
        stats.forEach { it.check(table) }
        TODO("Finish off")
    }
}