package wacc.frontend.ast.assign

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.StructAccessAST
import wacc.frontend.ast.type.TypeAST

class StructFieldAssignAST(val structAccess: StructAccessAST, val rhs: RhsAST): LhsAST, AbstractAST() {
//
//    override fun check(table: SymbolTable): Boolean {
//        //TODO("NOTYET")
//    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        TODO("Not yet implemented")
    }
}