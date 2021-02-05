package wacc.frontend.ast.function

import wacc.frontend.ast.AST
import wacc.frontend.ast.StatAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.IdentAST

class FuncAST(type: TypeAST, ident: IdentAST, paramList: List<ParamAST>, stat: StatAST) : AST