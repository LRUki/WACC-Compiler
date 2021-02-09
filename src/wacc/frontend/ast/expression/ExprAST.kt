package wacc.frontend.ast.expression

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SemanticAnalyser
import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.BaseType
import wacc.frontend.ast.BaseTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.RhsAST

interface ExprAST : RhsAST

// Result == "no"
// + (+ 3 4) 6
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST {
//    lateinit var type : Type
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        expr1.check(table)
        val type1 = expr1.getRealType(table)
        expr2.check(table)
        val type2 = expr2.getRealType(table)

        if (!type1.equals(type2)) {
            semanticError("Expected type $type1 actual type $type2")
        }
        when (binOp) {
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                if (type1.equals(SemanticAnalyser.defIntTypeAST)) {
                    return true
                }
                semanticError("Expected type Int actual type $type1")
            }
            BinOp.LTE, BinOp.LT, BinOp.GTE, BinOp.GT -> {
                if (type1.equals(SemanticAnalyser.defIntTypeAST) ||
                        type1.equals(SemanticAnalyser.defCharTypeAST)) {
                    return true
                }
                semanticError("Expected type Int or Char actual type $type1")
            }
            BinOp.AND, BinOp.OR -> {
                if (type1.equals(SemanticAnalyser.defBoolTypeAST)) {
                    return true
                }
                semanticError("Expected type Bool actual type $type1")
            }
            else -> return true
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return when (binOp) {
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {BaseTypeAST(BaseType.INT)}

            BinOp.EQ, BinOp.NEQ, BinOp.LTE, BinOp.LT,
            BinOp.GTE, BinOp.GT, BinOp.AND, BinOp.OR -> {BaseTypeAST(BaseType.BOOL)}
        }
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
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        val exprType = expr.getRealType(table)

        when (unOp) {
            UnOp.NOT -> {
                if (exprType.equals(SemanticAnalyser.defBoolTypeAST)) {
                    return true
                }
                semanticError("Expected type Bool, actual type $exprType")
            }
            UnOp.MINUS, UnOp.CHR -> {
                if (exprType.equals(SemanticAnalyser.defIntTypeAST)) {
                    return true
                }
                semanticError("Expected type Int, actual type $exprType")
            }
            UnOp.LEN -> {
                if (exprType.equals(SemanticAnalyser.defArrayTypeAST)) {
                    return true
                }
                semanticError("Expected type Array Actual type $exprType")
            }
            UnOp.ORD -> {
                if (exprType.equals(SemanticAnalyser.defCharTypeAST)) {
                    return true
                }
                semanticError("Expected type Char, actual type $exprType")
            }
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return when (unOp) {
            UnOp.NOT -> BaseTypeAST(BaseType.BOOL)
            UnOp.CHR -> BaseTypeAST(BaseType.CHAR)
            UnOp.MINUS, UnOp.LEN, UnOp.ORD -> BaseTypeAST(BaseType.INT)
        }
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
