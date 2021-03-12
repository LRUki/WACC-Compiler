package wacc.frontend.ast.assign

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.type.StructTypeAST
import wacc.frontend.ast.type.TypeAST

class StructAssignAST(val assignments: List<RhsAST>) : RhsAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return StructTypeAST()
    }

}