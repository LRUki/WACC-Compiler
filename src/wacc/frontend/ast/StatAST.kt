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

class SkipStatAST : StatAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

// int x = 5 + 6;
//::= hexpri
//j harray-liter i
//j `newpair' `(' hexpri `,' hexpri `)'
//j hpair-elemi
//j `call' hidenti `(' harg-listi? `)'
class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST): StatAST, Identifiable {
    override fun check(): Boolean {
        rhs.check()
        val rhsType = rhs.getRealType()
        if (type.equals(rhsType)) {
            println("Types are equal")
        } else {
            println("Types are not equal")
        }
        //TODO need to check its a valid type -- add to top level symbol table
        val isTypeCorrect = SymbolTable.currentST.lookupAll(getTypeString(type))
        val identName = SymbolTable.currentST.lookup(ident.name)
        //Does exist in scope
        return true
    }
}

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST {
    override fun check(): Boolean {
        lhs.check()
        rhs.check()
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
