package wacc.frontend.ast

import wacc.frontend.*
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

interface StatAST : AST

class SkipStatAST : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

// int x =
//::= hexpri
//j harray-liter i
//j `newpair' `(' hexpri `,' hexpri `)'
//j hpair-elemi
//j `call' hidenti `(' harg-listi? `)'
class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST {
    override fun check(): Boolean {
        val isTypeCorrect = SymbolTable.currentST.lookupAll(getTypeString(type))

        val identName = SymbolTable.currentST.lookup(ident.name)
        val rhsType = getRhsType(rhs)
        TODO()
    }
}

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class ReadStatAST(val expr: LhsAST) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class BlockStatAST(val body: List<StatAST>) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class MultiStatAST(val stats: List<StatAST>) : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}
