package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.LhsAST

class IdentAST(val name: String) : ExprAST, LhsAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return name
    }

    override fun getRealType(): TypeAST {
        TODO("Symbol table lookup of name from current scope upward")
    }
}
