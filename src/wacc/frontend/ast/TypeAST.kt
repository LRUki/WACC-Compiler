package wacc.frontend.ast

import wacc.frontend.SymbolTable

interface TypeAST : AST {
    // Compares the underlying type in two TypeASTs
    override fun equals(other: Any?): Boolean

    // Checks that the type exits in the symbol table
    fun isValidType(table: SymbolTable): Boolean {
        return true
    }
}

enum class BaseType(val innerType: BaseTypeAST? = null) {
    INT, BOOL, CHAR, STRING, ANY, ARRAY()
}

class BaseTypeAST(val type: BaseType) : TypeAST {

    override fun equals(other: Any?): Boolean {
        if (other is TypeAST) {
            return type == (other as BaseTypeAST).type
        }
        return false
    }

    override fun isValidType(table: SymbolTable): Boolean {
        return table.lookup(type.name.toLowerCase()).isPresent
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }

    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST,Identifiable {

    override fun equals(other: Any?): Boolean {
        if (other is TypeAST) {
            return other.equals(type)
        }
        return false
    }

    override fun isValidType(table: SymbolTable): Boolean {
        return type.isValidType(table)
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + dimension
        return result
    }

    //compare dimension here
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST,Identifiable {
    override fun equals(other: Any?): Boolean {
        if (other is InnerPairTypeAST) {
            return true
        } else if (other is PairTypeAST) {
            return type1.equals(other.type1) && type2.equals(other.type2)
        }
        return false
    }

    override fun isValidType(table: SymbolTable): Boolean {
        return type1.isValidType(table) && type2.isValidType(table)
    }

    override fun hashCode(): Int {
        var result = type1.hashCode()
        result = 31 * result + type2.hashCode()
        return result
    }

    // innerpair types
    // pair (pair (1, 2)) 3
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }
}


//TODO think about this later
class InnerPairTypeAST : TypeAST {
    // For pairElemType: baseType PAIR ;
    override fun equals(other: Any?): Boolean {
        if (other is PairTypeAST || other is InnerPairTypeAST) {
            return true
        }
        return false
    }

    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
