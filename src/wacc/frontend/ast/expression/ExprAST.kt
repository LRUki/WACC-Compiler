package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.ast.type.TypeInstance.boolTypeInstance
import wacc.frontend.ast.type.TypeInstance.charTypeInstance
import wacc.frontend.ast.type.TypeInstance.intTypeInstance
import wacc.frontend.ast.type.TypeInstance.stringTypeInstance
import wacc.frontend.exception.semanticError
import java.util.function.BiFunction
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

interface ExprAST : RhsAST

/**
 * AST node to represent an expression with a Binary Operation
 *
 * @property binOp Operator to perform to expressions, will be one defined in the BinOp Enum
 * @property expr1 First Expression
 * @property expr2 Second Expression
 */
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr1.check(table) || !expr2.check(table)) {
            return false
        }

        val type1 = expr1.getRealType(table)
        val type2 = expr2.getRealType(table)

        if (type1 != type2) {
            semanticError("Expected type $type1, Actual type $type2", ctx)
            return false
        }

        when (binOp) {
            IntBinOp.MULT, IntBinOp.DIV, IntBinOp.MOD,
            IntBinOp.PLUS, IntBinOp.MINUS -> {
                if (type1 == intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $type1", ctx)
            }
            CmpBinOp.LTE, CmpBinOp.LT, CmpBinOp.GTE, CmpBinOp.GT -> {
                if (type1 == intTypeInstance ||
                        type1 == charTypeInstance ||
                        type1 == stringTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, CHAR or STRING, Actual type $type1", ctx)
            }
            BoolBinOp.AND, BoolBinOp.OR -> {
                if (type1 == boolTypeInstance) {
                    return true
                }
                semanticError("Expected type BOOL, Actual type $type1", ctx)
            }
            else -> return true
        }

        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return when (binOp) {
            IntBinOp.MULT, IntBinOp.DIV, IntBinOp.MOD,
            IntBinOp.PLUS, IntBinOp.MINUS -> {
                BaseTypeAST(BaseType.INT)
            }
            else -> {
                BaseTypeAST(BaseType.BOOL)
            }
        }
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitBinOpExprAST(this)
    }

}


interface BinOp {
}

enum class IntBinOp : BinOp, BiFunction<Int,Int,Int> {
    PLUS {
        override fun apply(t: Int, u: Int): Int {
            return t + u
        }

    },
    MINUS {
        override fun apply(t: Int, u: Int): Int {
            return  t - u
        }
    },
    MULT {
        override fun apply(t: Int, u: Int): Int {
            return t * u
        }
    },
    DIV {
        override fun apply(t: Int, u: Int): Int {
            return t / u
        }
    },
    MOD {
        override fun apply(t: Int, u: Int): Int {
            return t % u
        }
    };


}

enum class BoolBinOp : BinOp, BiFunction<Boolean, Boolean, Boolean> {
    AND{
        override fun apply(t: Boolean, u: Boolean): Boolean {
            return t and u
        }
    },
    OR {
        override fun apply(t: Boolean, u: Boolean): Boolean {
            return t or u
        }
    }
}

enum class CmpBinOp:BinOp{
    LTE,
    LT,
    GTE,
    GT,
    EQ,
    NEQ,
}

//fun main(){
//    val a = IntBinOp.DIV
//    val b = BinOpExprAST(a,IntLiterAST(3),IntLiterAST(3))
//    when (b.binOp){
//        BoolBinOp.AND -> {println("bool fuck")}
//        IntBinOp.PLUS -> {println("oh no")}
//        IntBinOp.DIV -> {println("here")}
//        else -> {
//            println("funck")}
//    }
//}

//enum class BinOp {
//    MULT, DIV, MOD,
//    PLUS, MINUS,
//    LTE, LT, GTE, GT,
//    EQ, NEQ,
//    AND,
//    OR
//}

/**
 * AST node to represent an expression with a Unary Operation
 *
 * @property unOp Operation to perform on the expression, chosen from the UnOp Enum
 * @property expr Expression to operate on
 */
class UnOpExprAST(val unOp: UnOp, val expr: ExprAST) : ExprAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        if (!expr.check(table)) {
            return false
        }
        val exprType = expr.getRealType(table)

        when (unOp) {
            UnOp.NOT -> {
                if (exprType == boolTypeInstance) {
                    return true
                }
                semanticError("Expected type BOOL, Actual type $exprType", ctx)
            }
            UnOp.MINUS, UnOp.CHR -> {
                if (exprType == intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $exprType", ctx)
            }
            UnOp.LEN -> {
                if (exprType is ArrayTypeAST) {
                    return true
                }
                semanticError("Expected type ARRAY, Actual type $exprType", ctx)
            }
            UnOp.ORD -> {
                if (exprType == charTypeInstance) {
                    return true
                }
                semanticError("Expected type CHAR, Actual type $exprType", ctx)
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

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitUnOpExprAST(this)
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
