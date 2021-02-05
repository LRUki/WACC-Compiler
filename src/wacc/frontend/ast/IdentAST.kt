package wacc.frontend.ast

import wacc.frontend.ast.assign.LhsAST

class IdentAST(val name: String) : ExprAST, LhsAST
