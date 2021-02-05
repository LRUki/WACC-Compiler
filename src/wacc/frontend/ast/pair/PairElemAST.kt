package wacc.frontend.ast.pair

import wacc.frontend.ast.ExprAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST

class PairElemAST(choice: PairChoice, expr: ExprAST): LhsAST, RhsAST

enum class PairChoice {
    FST,
    SND
}