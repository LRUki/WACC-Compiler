package wacc.frontend.ast

import wacc.frontend.*
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

interface StatAST : AST

class SkipStatAST : StatAST
// int x = 5 + 6;
//::= hexpri
//j harray-liter i
//j `newpair' `(' hexpri `,' hexpri `)'
//j hpair-elemi
//j `call' hidenti `(' harg-listi? `)'
class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST): StatAST, Identifiable {
    override fun check(table: SymbolTable): Boolean {
        rhs.check(table)
        val identName = table.lookup(ident.name)
        val rhsType = rhs.getRealType()
        if (!identName.isEmpty) {
            semanticError("Variable with that name already exists")
        }
        val isTypeCorrect = table.lookupAll()
        //TODO need to check its a valid type -- add to top level symbol table

        if (!type.equals(rhsType)) {
            semanticError("Type mismatch - LHS and RHS not compatible")//TODO(Make error message better)
//            semanticError("Expected type $type but actual type $rhsType")
        }
        table.add(ident.name, this)

        return true
    }
}

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class ReadStatAST(val expr: LhsAST) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class BlockStatAST(val body: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class MultiStatAST(val stats: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}
