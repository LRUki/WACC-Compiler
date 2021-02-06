package wacc.frontend.ast.expression

import wacc.frontend.ast.assign.LhsAST

class IdentAST(val name: String) : ExprAST, LhsAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return name
    }
}
