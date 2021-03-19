package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.*
import wacc.frontend.ast.type.TypeInstance.boolTypeInstance
import wacc.frontend.ast.type.TypeInstance.charTypeInstance
import wacc.frontend.ast.type.TypeInstance.intTypeInstance
import wacc.frontend.ast.type.TypeInstance.stringTypeInstance
import wacc.frontend.exception.semanticError
import java.util.function.BiFunction

interface ExprAST : RhsAST {
    fun weight(): Int {
        return 1
    }
}

interface OpExpr {
    fun setAllVariableAccessedFlags()
    fun setMemoryReferencesAccessed()
}

/**
 * AST node to represent an expression with a Binary Operation
 *
 * @property binOp Operator to perform to expressions, will be one defined in the BinOp Enum
 * @property expr1 First Expression
 * @property expr2 Second Expression
 */
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST, OpExpr, AbstractAST() {
    var weight = -1
    var pointerOp = false
    var shiftOffset = 0

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr1.check(table) || !expr2.check(table)) {
            return false
        }

        val type1 = expr1.getRealType(table)
        val type2 = expr2.getRealType(table)

        // Allow pointer arithmetic
        if (type1 is PointerTypeAST && type2 == intTypeInstance
                && (binOp == IntBinOp.PLUS || binOp == IntBinOp.MINUS)) {
            pointerOp = true
            shiftOffset = when (type1.type) {
                charTypeInstance -> 0
                boolTypeInstance -> 0
                else -> 2
            }
            return true
        }

        if (type1 != type2) {
            semanticError("Expected type $type1, Actual type $type2", ctx)
            return false
        }

        when (binOp) {
            is IntBinOp -> {
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
            is BoolBinOp -> {
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
        // Allow pointer arithmetic
        if (pointerOp) {
            return expr1.getRealType(table)
        }

        return if (binOp is IntBinOp)
            BaseTypeAST(BaseType.INT)
        else
            BaseTypeAST(BaseType.BOOL)
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitBinOpExprAST(this)
    }

    override fun weight(): Int {
        if (weight < 0) {
            val c1 = Math.max(expr1.weight(), expr2.weight() + 1) //if we select e1 first
            val c2 = Math.max(expr1.weight() + 1, expr2.weight()) //if we select e2 first
            weight = Math.min(c1, c2)
        }
        return weight
    }

    override fun setAllVariableAccessedFlags() {
        if (expr1 is IdentAST) {
            symTable.setAccessedField(expr1.name)
        }
        if (expr2 is IdentAST) {
            symTable.setAccessedField(expr2.name)
        }
        if (expr1 is OpExpr) {
            expr1.setAllVariableAccessedFlags()
        }
        if (expr2 is OpExpr) {
            expr2.setAllVariableAccessedFlags()

        }

    }

    override fun setMemoryReferencesAccessed() {
        if (expr1 is OpExpr) {
            expr1.setMemoryReferencesAccessed()
        }
        if (expr2 is OpExpr) {
            expr2.setMemoryReferencesAccessed()
        }

    }


}


interface BinOp

enum class IntBinOp : BinOp, BiFunction<Int, Int, Int> {
    PLUS {
        override fun apply(t: Int, u: Int): Int {
            return t + u
        }

    },
    MINUS {
        override fun apply(t: Int, u: Int): Int {
            return t - u
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
    AND {
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

enum class CmpBinOp : BinOp {
    LTE,
    LT,
    GTE,
    GT,
    EQ,
    NEQ,
}


/**
 * AST node to represent an expression with a Unary Operation
 *
 * @property unOp Operation to perform on the expression, chosen from the UnOp Enum
 * @property expr Expression to operate on
 */
class UnOpExprAST(val unOp: UnOp, val expr: ExprAST) : ExprAST, OpExpr, AbstractAST() {
    var weight = -1
    override fun check(table: SymbolTable): Boolean {
        symTable = table
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
            UnOp.REF -> {
                if (expr is IdentAST || expr is ArrayElemAST) {
                    return true
                }
                semanticError("Referencing is not supported for a value not in memory", ctx)
            }
            UnOp.DEREF -> {
                if (exprType is PointerTypeAST) {
                    return true
                }
                semanticError("Unable to dereference non-pointer type $exprType", ctx)

            }
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return when (unOp) {
            UnOp.NOT -> BaseTypeAST(BaseType.BOOL)
            UnOp.CHR -> BaseTypeAST(BaseType.CHAR)
            UnOp.MINUS, UnOp.LEN, UnOp.ORD -> BaseTypeAST(BaseType.INT)
            UnOp.REF -> {
                val exprType = expr.getRealType(table)
                PointerTypeAST(exprType)
            }
            UnOp.DEREF -> {
                val exprType = expr.getRealType(table)
                (exprType as PointerTypeAST).type
            }
        }
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitUnOpExprAST(this)
    }

    override fun weight(): Int {
        if (weight < 0) {
            weight = expr.weight()
        }
        return weight
    }

    override fun setAllVariableAccessedFlags() {
        if (expr is IdentAST) {
            symTable.setAccessedField(expr.name)
        }
        if (expr is OpExpr) {
            expr.setAllVariableAccessedFlags()
        }
        if (expr is ArrayElemAST) {
            symTable.setAccessedField(expr.ident.name)
        }
    }

    override fun setMemoryReferencesAccessed() {
        if (unOp == UnOp.REF) {
            if (expr is IdentAST) {
                symTable.setAccessedField(expr.name)
                symTable.setAssignedField(expr.name)
            } else if (expr is ArrayElemAST) {
                symTable.setAccessedField(expr.ident.name)
                symTable.setAssignedField(expr.ident.name)
            }
        } else if (expr is OpExpr) {
            expr.setMemoryReferencesAccessed()
        }

    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR, REF, DEREF
}
