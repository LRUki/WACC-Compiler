package wacc.frontend.ast.function

import wacc.frontend.ast.AST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.TypeAST

/**
 * AST node to represent a Parameter
 *
 * @property type Type of the parameter
 * @property ident Name of the parameter
 */
class ParamAST(val type: TypeAST, val ident: IdentAST) : AST, Identifiable {
      override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitParamAST(this)
    }

}
