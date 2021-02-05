package wacc.frontend.ast.array

import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.assign.LhsAST

class ArrayElemAST(ident: IdentAST, indices: List<ExprAST>): ExprAST, LhsAST
