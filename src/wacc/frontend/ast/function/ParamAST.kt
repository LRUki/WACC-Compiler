package wacc.frontend.ast.function

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.Identifiable
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.IdentAST

class ParamAST(val type: TypeAST, val ident: IdentAST): AST, Identifiable {

    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        //Check that the type is valid
        return true
    }
}
