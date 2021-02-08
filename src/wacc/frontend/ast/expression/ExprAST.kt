package wacc.frontend.ast.expression

import wacc.frontend.SemanticAnalyser
import wacc.frontend.SymbolTable
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.exception.SemanticException

interface ExprAST : RhsAST

// Result == "no"
// + (+ 3 4) 6
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST {
//    lateinit var type : Type

    override fun check(table: SymbolTable): Boolean {
        expr1.check(table)
        val type1 = expr1.getRealType(table)
        expr2.check(table)
        val type2 = expr2.getRealType(table)

        if (!type1.equals(type2)) {
            SemanticException("Expected type $type1 actual type $type2")
        }
        when (binOp) {
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                if (type1.equals(SemanticAnalyser.defIntTypeAST)) {
                    return true
                }
                SemanticException("Expected type Int actual type $type1")
            }
            BinOp.LTE, BinOp.LT, BinOp.GTE, BinOp.GT -> {
                if (type1.equals(SemanticAnalyser.defIntTypeAST) ||
                        type1.equals(SemanticAnalyser.defCharTypeAST)) {
                    return true
                }
                SemanticException("Expected type Int or Char actual type $type1")
            }
            BinOp.AND, BinOp.OR -> {
                if (type1.equals(SemanticAnalyser.defBoolTypeAST)) {
                    return true
                }
                SemanticException("Expected type Bool actual type $type1")
            }
            else -> return true
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return expr1.getRealType(table)
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
    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        val exprType = expr.getRealType(table)

        when (unOp) {
            UnOp.NOT -> {
                if (exprType.equals(SemanticAnalyser.defBoolTypeAST)) {
                    return true
                }
                SemanticException("Expected type Bool, actual type $exprType")
            }
            UnOp.MINUS, UnOp.CHR -> {
                if (exprType.equals(SemanticAnalyser.defIntTypeAST)) {
                    return true
                }
                SemanticException("Expected type Int, actual type $exprType")
            }
            UnOp.LEN -> {
                if (exprType.equals(SemanticAnalyser.defArrayTypeAST)) {
                    return true
                }
                SemanticException("Expected type Array Actual type $exprType")
            }
            UnOp.ORD -> {
                if (exprType.equals(SemanticAnalyser.defCharTypeAST)) {
                    return true
                }
                SemanticException("Expected type Char, actual type $exprType")
            }
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return expr.getRealType(table)
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
