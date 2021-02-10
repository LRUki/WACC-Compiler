package wacc.frontend.ast.type


import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.AbstractAST

interface TypeAST : AST {
    // Compares the underlying type in two TypeASTs
    override fun equals(other: Any?): Boolean

    override fun toString(): String
}

enum class BaseType {
    INT, BOOL, CHAR, STRING, ANY, NULL
}

class BaseTypeAST(val type: BaseType) : TypeAST, AbstractAST() {

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

    override fun toString(): String {
        return type.name
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST, Identifiable {

    override fun equals(other: Any?): Boolean {
        if (other is ArrayTypeAST) {
            return other.type.equals(type)
        }
        return false
    }

    override fun toString(): String {
        var currentType = type
        var counter = 0
        while (currentType is ArrayTypeAST){
            counter++
            currentType = currentType.type
        }
        return "$type" + "[]".repeat(counter)
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

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST, Identifiable {
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

    override fun toString(): String {
        return "pair(${type1}, ${type2})"
    }

    override fun hashCode(): Int {
        var result = type1.hashCode()
        result = 31 * result + type2.hashCode()
        return result
    }

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

    override fun toString(): String {
        return "pair"
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
