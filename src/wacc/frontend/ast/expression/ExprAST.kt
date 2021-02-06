package wacc.frontend.ast.expression

import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.identifiers.Type


interface ExprAST : RhsAST
// Result == "no"
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST {
    lateinit var type : Type

    override fun check(): Boolean {
        TODO()

//        get types of exp1 and exp2
//        check types same and either bool or int
//        type = ---
        TODO()
    }
}

enum class BinOp {
    MULT, DIV, MOD,
    PLUS, MINUS,
    LTE, LT, GTE, GT,
    EQ, NEQ,
    AND,
    OR
}

class UnOpExprAST(val unOp: UnOp, val expr: ExprAST) : ExprAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
