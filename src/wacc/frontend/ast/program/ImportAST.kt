package wacc.frontend.ast.program

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST

class ImportAST(val ident: IdentAST) : AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        val filename = ident.name
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

}