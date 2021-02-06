package wacc.frontend.ast.function

import wacc.frontend.ast.AST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.IdentAST

class ParamAST(val type: TypeAST, val ident: IdentAST): AST
