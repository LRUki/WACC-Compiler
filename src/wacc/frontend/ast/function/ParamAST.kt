package wacc.frontend.ast.function

import wacc.frontend.ast.AST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.TypeAST

class ParamAST(val type: TypeAST, val ident: IdentAST) : AST, Identifiable {}
