package wacc.frontend.ast

interface ExprAST : RhsAST

class BinOpExprAST(val binOp : BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST

enum class BinOp {
    MULT, DIV, MOD,
    PLUS, MINUS,
    LTE, LT, GTE, GT,
    EQ, NEQ,
    AND,
    OR
}
