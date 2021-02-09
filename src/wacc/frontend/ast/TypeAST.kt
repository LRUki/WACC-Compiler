package wacc.frontend.ast

import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable

interface TypeAST : AST {
    // Compares the underlying type in two TypeASTs
    override fun equals(other: Any?): Boolean

    // Checks that the type exits in the symbol table
    fun isValidType(table: SymbolTable): Boolean {
        return true
    }
}

enum class BaseType {
    INT, BOOL, CHAR, STRING, ANY, NULL
}

class BaseTypeAST(val type: BaseType) : TypeAST {

    override fun equals(other: Any?): Boolean {
        if (other is BaseTypeAST) {
            if (type == BaseType.ANY || other.type == BaseType.ANY) {
                return true
            }
            return type == other.type
        } else if (other is ArrayTypeAST) {
            return this.equals(other.type)
        } else if (other is PairTypeAST) {
            return type == BaseType.NULL
        }
        return false
    }

    override fun isValidType(table: SymbolTable): Boolean {
        return table.lookupAll(type.name.toLowerCase()).isPresent
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }

    override fun check(table: SymbolTable): Boolean {
         if (table.lookupAll(type.name.toLowerCase()).isEmpty) {
             semanticError("Invalid type $type does not exist")
         }
        return true
    }
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST,Identifiable {

    override fun equals(other: Any?): Boolean {
        if (other is ArrayTypeAST) {
            return other.type.equals(type)
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
        type.check(table)
        return true
    }
}

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST,Identifiable {
    override fun equals(other: Any?): Boolean {
        if (other is InnerPairTypeAST) {
            return true
        } else if (other is PairTypeAST) {
            return type1.equals(other.type1) && type2.equals(other.type2)
        } else if (other is ArrayTypeAST) {
            return this.equals(other.type)
        } else if (other is BaseTypeAST) {
            if (other.type.equals(BaseType.NULL)) {
                return true
            }
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
        type1.check(table)
        type2.check(table)
        return true
    }
}


//TODO think about this later
class InnerPairTypeAST : TypeAST {
    // For pairElemType: baseType PAIR ;
    override fun equals(other: Any?): Boolean {
        if (other is PairTypeAST || other is InnerPairTypeAST ||
                other is BaseTypeAST && other.type == BaseType.NULL) {
            return true
        }
        return false
    }

    override fun isValidType(table: SymbolTable): Boolean {
        return table.lookupAll("pair").isPresent
    }


    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
