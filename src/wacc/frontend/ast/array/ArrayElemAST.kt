package wacc.frontend.ast.array

import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.assign.LhsAST

class ArrayElemAST(val ident: IdentAST, val indices: List<ExprAST>): ExprAST, LhsAST {
    override fun getRealType(): TypeAST {
        TODO("Not yet implemented")
    }
}
