package wacc.frontend.ast

import org.antlr.v4.runtime.ParserRuleContext
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


interface StatAST : AST

class SkipStatAST : StatAST

// int x = 5 + 6;
class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST, Identifiable {
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        rhs.check(table)
        val identName = table.lookup(ident.name)
        val rhsType = rhs.getRealType(table)
        if (identName.isPresent && identName.get() !is FuncAST) {
            semanticError("Variable with that name already exists")
        }

        if (!type.equals(rhsType)) {
            semanticError("Type mismatch - Expected type $type but actual type $rhsType")
//            semanticError("Expected type $type but actual type $rhsType")
        }
        table.add(ident.name, this)
        return true
    }
}

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST {

    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    private fun lhsIsAFunction(table: SymbolTable) :Boolean {
        if (lhs is IdentAST) {
            val fName = table.lookupAll(lhs.name)
            if (fName.isPresent && fName.get() is FuncAST) {
                return true
            }
        }
        return false
    }

    override fun check(table: SymbolTable): Boolean {
        lhs.check(table)
        rhs.check(table)
        var leftType = lhs.getRealType(table)
        val rightType = rhs.getRealType(table)
        if (leftType is ArrayTypeAST) {
            leftType = leftType.type
        }
        if (lhsIsAFunction(table)) {
            semanticError("Cannot assign a value to a function")
        }

        if (!leftType.equals(rightType)) {
            semanticError("Types $leftType and $rightType are not equal.")
        }
        return true;
    }
}

class ReadStatAST(val expr: LhsAST) : StatAST {
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext? {
        return ctx;
    }

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
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        val exprType = expr.getRealType(table)
        when (action) {
            Action.FREE -> {
                if (exprType is ArrayTypeAST || exprType is PairTypeAST) {
                    return true;
                }
                semanticError("Actual type ${exprType}: Expected Array or Pair type")
            }
            Action.RETURN -> {
                val closestFunc = table.lookupFirstFunc()
                if (closestFunc.isEmpty) {
                    semanticError("A return token is outside of a function scope")
                }
                val returnType = (closestFunc.get() as FuncAST).type
                if (!returnType.equals(exprType)) {
                    semanticError("Expected $returnType but actual type $exprType")
                }
                return true
            }
            Action.EXIT -> {
                if (exprType.equals(defIntTypeAST)) {
                    return true
                }
                semanticError("Expected type INT actual type $exprType")
            }
            Action.PRINT -> {
                return expr.check(table)
            }
            Action.PRINTLN -> {
                return expr.check(table)
            }
        }
        return false
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST {

    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        //cond is bool
        cond.check(table)
        val condType = cond.getRealType(table)
        if (!condType.equals(defBoolTypeAST)) {
            semanticError("If condition must be a Boolean but was $condType")
        }

        val thenST = SymbolTable(table)
        thenBody.forEach { it.check(thenST) }

        val elseST = SymbolTable(table)
        elseBody.forEach { it.check(elseST) }

        return true;
    }
}

class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST {
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        cond.check(table)
        val condType = cond.getRealType(table)
        if (!condType.equals(defBoolTypeAST)) {
            semanticError("If condition must be a Boolean but was $condType")
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
