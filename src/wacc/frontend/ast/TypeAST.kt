package wacc.frontend.ast

import wacc.frontend.SymbolTable


interface TypeAST : AST {
    fun equals(that: TypeAST): Boolean
}

enum class BaseType {
    INT, BOOL, CHAR, STRING, ANY
}

class BaseTypeAST(val type: BaseType) : TypeAST {
    override fun equals(that: TypeAST): Boolean {
        return type == (that as BaseTypeAST).type
    }

    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST {
    override fun equals(that: TypeAST): Boolean {
        return that.equals(type)
    }

    //compare dimension here
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST {
    override fun equals(that: TypeAST): Boolean {
        if (that is InnerPairTypeAST) {
            return true
        } else if (that is PairTypeAST) {
            return type1.equals(that.type1) && type2.equals(that.type2)
        }
        return false
    }

    // innerpair types
    // pair (pair (1, 2)) 3
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

//TODO think about this later
class InnerPairTypeAST : TypeAST {// For pairElemType: baseType PAIR ;
    override fun equals(that: TypeAST): Boolean {
        if (that is PairTypeAST || that is InnerPairTypeAST) {
            return true
        }
        return false
    }

    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}
