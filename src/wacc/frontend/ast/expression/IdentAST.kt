package wacc.frontend.ast.expression

import wacc.frontend.ast.assign.LhsAST

class IdentAST(val name: String) : ExprAST, LhsAST {
    override fun toString(): String {
        return name
    }
}
