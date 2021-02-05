package wacc.frontend.ast

import wacc.frontend.ast.assign.RhsAST

interface ExprAST : RhsAST

class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST

enum class BinOp {
    MULT, DIV, MOD,
    PLUS, MINUS,
    LTE, LT, GTE, GT,
    EQ, NEQ,
    AND,
    OR
}

class UnOpExprAST(val unOp: UnOp, val expr: ExprAST) : ExprAST

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
