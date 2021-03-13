package wacc.frontend.ast.statement.nonblock

import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.StructFieldAST

class StructDeclareAST(val ident: IdentAST, val fields: List<StructFieldAST>) : Identifiable, StatAST, AbstractAST() {
    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

}

