package wacc.frontend.ast.program

import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST

class ImportAST(val filename: IdentAST) : AbstractAST() {

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

}