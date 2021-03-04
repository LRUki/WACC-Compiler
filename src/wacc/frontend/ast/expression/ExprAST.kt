package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.RuntimeError
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.ShiftType
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
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
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                if (type1 == intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $type1", ctx)
            }
            BinOp.LTE, BinOp.LT, BinOp.GTE, BinOp.GT -> {
                if (type1 == intTypeInstance ||
                        type1 == charTypeInstance ||
                        type1 == stringTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, CHAR or STRING, Actual type $type1", ctx)
            }
            BinOp.AND, BinOp.OR -> {
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
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                BaseTypeAST(BaseType.INT)
            }

            BinOp.EQ, BinOp.NEQ, BinOp.LTE, BinOp.LT,
            BinOp.GTE, BinOp.GT, BinOp.AND, BinOp.OR -> {
                BaseTypeAST(BaseType.BOOL)
            }
        }
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitBinOpExprAST(this)
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
