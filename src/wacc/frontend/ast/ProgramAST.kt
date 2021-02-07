package wacc.frontend.ast

import wacc.frontend.ast.function.FuncAST

class ProgramAST(val funcList: List<FuncAST>, val stats: List<StatAST>) : AST {
    override fun check(): Boolean {
        funcList.forEach { it.check() }
        stats.forEach { it.check() }
        TODO("Finish off")
    }
}