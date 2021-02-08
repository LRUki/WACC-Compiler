package wacc.frontend.ast.array

import wacc.frontend.SymbolTable
import wacc.frontend.ast.BaseType.ARRAY
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.assign.LhsAST

class ArrayElemAST(val ident: IdentAST, val indices: List<ExprAST>): ExprAST, LhsAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return ident.getRealType(table)
    }
}
