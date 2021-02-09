package wacc.frontend.ast

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SymbolTable
import wacc.frontend.ast.function.FuncAST

class ProgramAST(val funcList: List<FuncAST>, val stats: List<StatAST>) : AST {

    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext? {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        funcList.forEach { it.checkNameAndAddToST(table) }
        funcList.forEach { it.check(table) }
        stats.forEach { it.check(table) }
        return true
    }

}