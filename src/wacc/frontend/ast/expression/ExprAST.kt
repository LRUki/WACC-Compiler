package wacc.frontend.ast.expression

import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.RhsAST

interface ExprAST : RhsAST
// Result == "no"
// + (+ 3 4) 6
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST {
//    lateinit var type : Type

    override fun check(): Boolean {
//        get types of exp1 and exp2
//        check types same and either bool or int
//        type = ---
        TODO()
    }

    override fun getRealType(): TypeAST {
        return expr1.getRealType()
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

    override fun getRealType(): TypeAST {
        return expr.getRealType()
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
