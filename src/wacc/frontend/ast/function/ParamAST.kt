package wacc.frontend.ast.function

import wacc.backend.translate.instr.Instr
import wacc.frontend.ast.AST
import wacc.frontend.ast.Translatable
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.TypeAST

/**
 * AST node to represent a Parameter
 *
 * @property type Type of the parameter
 * @property ident Name of the parameter
 */
class ParamAST(val type: TypeAST, val ident: IdentAST) : AST, Identifiable, Translatable {
    override fun translate(): List<Instr> {
        return emptyList()
    }
}
