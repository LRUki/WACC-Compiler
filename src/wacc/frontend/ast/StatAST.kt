package wacc.frontend.ast

import wacc.frontend.*
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.pair.PairElemAST
import java.util.*

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
        val isTypeCorrect = table.lookupAll(ident.name)
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
        lhs.check(table)
        rhs.check(table)
        val leftType = lhs.getRealType()
        val rightType = rhs.getRealType()
        var identifiable: Optional<Identifiable> = Optional.empty()
        if(lhs is IdentAST){
            identifiable = SymbolTable.currentST.lookupAll(lhs.name)
        }
        if(lhs is ArrayElemAST){
            identifiable = SymbolTable.currentST.lookupAll(lhs.ident.name)
        }
        if(lhs is PairElemAST){
            //nothing needs doing
        }
        if(identifiable.isPresent){
            if(!leftType.equals(rightType)){
                println("Types $leftType and $rightType are not equal.")
                println("Assignment cannot occur.")
                return false;
            }
            return true;
        }
        println("Left hand side has not been declared previously in the program.")
        return false;
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
