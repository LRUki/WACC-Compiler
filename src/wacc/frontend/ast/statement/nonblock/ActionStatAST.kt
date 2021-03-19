package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.expression.BinOpExprAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.OpExpr
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.*
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Action Statement
 *
 * @property action An action declared in the ACTION enum
 * @property expr Expression to perform action on
 */
class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr.check(table)) {
            return false
        }
        val exprType = expr.getRealType(table)
        when (expr) {
            is IdentAST -> {
                table.setAccessedField(expr.name)
            }
            is ArrayElemAST -> {
                table.setAccessedField(expr.ident.name)
            }
            is OpExpr -> {
                expr.setAllVariableAccessedFlags()
            }
        }
        when (action) {
            Action.FREE -> {
                if (exprType is ArrayTypeAST || exprType is PairTypeAST || exprType is StructTypeAST) {
                    return true
                }
                semanticError("Expected type ARRAY or PAIR, Actual type $exprType", ctx)
            }
            Action.RETURN -> {
                val closestFunc = table.lookupFirstFunc()
                if (closestFunc.isEmpty) {
                    semanticError("A return token is outside of a function scope", ctx)
                    return false
                }
                val returnType = (closestFunc.get()).type
                if (returnType != exprType) {
                    semanticError("Expected type $returnType, Actual type $exprType", ctx)
                    return false
                }
                return true
            }
            Action.EXIT -> {
                if (exprType == TypeInstance.intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $exprType", ctx)
            }
            Action.PRINT, Action.PRINTLN -> {
                return expr.check(table)
            }
        }
        return false
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitActionStatAST(this)
    }

}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class VoidReturnStatAST() : StatAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        symTable = table
        val exprType = VoidTypeAST()

        val closestFunc = table.lookupFirstFunc()
        if (closestFunc.isEmpty) {
            semanticError("A return token is outside of a function scope", ctx)
            return false
        }
        val returnType = (closestFunc.get()).type
        if (returnType != exprType) {
            semanticError("Expected type $returnType, Actual type $exprType", ctx)
            return false
        }
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitVoidReturnStatAST(this)
    }

}
