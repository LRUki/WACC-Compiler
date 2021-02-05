package wacc.frontend.ast.function

import wacc.frontend.ast.AST
import wacc.frontend.ast.StatAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.IdentAST

class FuncAST(val type: TypeAST, val ident: IdentAST, val paramList: List<ParamAST>, val body: List<StatAST>) : AST {
    override fun toString(): String {
        return ident.toString()
    }
}