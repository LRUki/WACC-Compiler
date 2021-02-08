package wacc.frontend.ast

import wacc.frontend.*
import wacc.frontend.SemanticAnalyser.Companion.defBoolTypeAST
import wacc.frontend.SemanticAnalyser.Companion.defCharTypeAST
import wacc.frontend.SemanticAnalyser.Companion.defIntTypeAST
import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.exception.SemanticException


interface StatAST : AST

class SkipStatAST : StatAST

// int x = 5 + 6;
//::= hexpri
//j harray-liter i
//j `newpair' `(' hexpri `,' hexpri `)'
//j hpair-elemi
//j `call' hidenti `(' harg-listi? `)'
class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST, Identifiable {
    override fun check(table: SymbolTable): Boolean {
        rhs.check(table)
        val identName = table.lookup(ident.name)
        val rhsType = rhs.getRealType(table)
        if (identName.isPresent) {
            semanticError("Variable with that name already exists")
        }
        if (!type.isValidType(table)) {
            semanticError("Type $type is not defined")
        }
        //TODO(Check that type is declarable)
        if (!type.equals(rhsType)) {
            semanticError("Type mismatch - Expected type $type but actual type $rhsType")
//            semanticError("Expected type $type but actual type $rhsType")
        }
        table.add(ident.name, this)
        return true
    }
}

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        lhs.check(table)
        rhs.check(table)
        val leftType = lhs.getRealType(table)
        val rightType = rhs.getRealType(table)
        if (!leftType.isValidType(table)) {
            semanticError("Left hand side type is not valid")
        }
        if (!rightType.isValidType(table)) {
            semanticError("Right hand side type is not valid")
        }
        if (!leftType.equals(rightType)) {
            semanticError("Types $leftType and $rightType are not equal.")
        }
        return true;
    }
}

class ReadStatAST(val expr: LhsAST) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        val exprType = expr.getRealType(table)
        if (!exprType.equals(defCharTypeAST) && !exprType.equals(defIntTypeAST)) {
            semanticError("Invalid type, must be Int or Char")
        }
        return true
    }
}

//int[] a = [0]
class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        val exprType = expr.getRealType(table)
        when (action) {
            Action.FREE -> {
                if (exprType is ArrayTypeAST || exprType is PairTypeAST) {
                    return true;
                }
                SemanticException("Actual type ${exprType}: Expected Array or Pair type")
            }
            Action.RETURN -> {
                val closestFunc = table.lookupFirstFunc()
                if (closestFunc.isEmpty) {
                    SemanticException("A return token is outside of a function scope")
                }
                val returnType = (closestFunc as FuncAST).type
                if (!returnType.equals(exprType)) {
                    SemanticException("Expected $returnType but actual type $exprType")
                }
                return true
            }
            Action.EXIT -> {
                if (exprType.equals(defIntTypeAST)) {
                    return true
                }
                SemanticException("Expected type INT actual type $exprType")
            }
            Action.PRINT -> {
                return true
            }
            Action.PRINTLN -> {
                return true
            }
        }
        return false
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        //cond is bool
        cond.check(table)
        val condType = cond.getRealType(table)
        if (!condType.equals(defBoolTypeAST)) {
            SemanticException("If condition must be a Boolean but was $condType")
        }

        val thenST = SymbolTable(table)
        thenBody.forEach { it.check(thenST) }

        val elseST = SymbolTable(table)
        elseBody.forEach { it.check(elseST) }

        return true;
    }
}

class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        cond.check(table)
        val condType = cond.getRealType(table)
        if (!condType.equals(defBoolTypeAST)) {
            SemanticException("If condition must be a Boolean but was $condType")
        }
        val blockST = SymbolTable(table)
        body.forEach { it.check(blockST) }
        return true
    }
}

class BlockStatAST(val body: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        val blockST = SymbolTable(table)
        body.forEach { it.check(blockST) }
        return true
    }
}

class MultiStatAST(val stats: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        val blockST = SymbolTable(table)
        stats.forEach { it.check(blockST) }
        return true
    }
}
