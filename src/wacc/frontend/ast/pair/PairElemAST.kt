package wacc.frontend.ast.pair

import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST

class PairElemAST(val choice: PairChoice, val expr: ExprAST): LhsAST, RhsAST

enum class PairChoice {
    FST,
    SND
}