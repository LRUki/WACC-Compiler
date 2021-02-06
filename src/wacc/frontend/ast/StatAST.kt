package wacc.frontend.ast

import wacc.frontend.SymbolTable
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.SemanticAnalyser
import wacc.frontend.printErr

interface StatAST : AST

class SkipStatAST : StatAST

class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST {
    override fun check(): Boolean {
//        val isTypeCorrect = SymbolTable.currentST.lookupAll(getTypeString(type))

        val identName = SymbolTable.currentST.lookup(ident.name)
        val rhsType = rhs.check()
        TODO()
    }
}

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST

class ReadStatAST(val expr: LhsAST) : StatAST

class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST

class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST

class BlockStatAST(val body: List<StatAST>) : StatAST

class MultiStatAST(val stats: List<StatAST>) : StatAST
