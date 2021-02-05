package wacc.frontend.ast.array

import wacc.frontend.ast.ExprAST
import wacc.frontend.ast.IdentAST
import wacc.frontend.ast.assign.LhsAST

class ArrayElemAST(ident: IdentAST, indices: List<ExprAST>): ExprAST, LhsAST
