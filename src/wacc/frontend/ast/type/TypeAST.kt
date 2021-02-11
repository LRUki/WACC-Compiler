package wacc.frontend.ast.type


import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.AbstractAST
/**
 * Implemented by Type AST nodes
 * Implements the AST interface to be able to override the check method
 */
interface TypeAST : AST {
    /**
     * Equals method used to match the underlying types
     * Enforces all TypeASTs must override equals
     *
     * @param other Other TypeAST
     * @return Boolean representing result of equality check
     */
    override fun equals(other: Any?): Boolean

    override fun toString(): String
}

enum class BaseType {
    INT, BOOL, CHAR, STRING, ANY, NULL
}

/**
 * AST node representing a Base Type
 * INT, CHAR, STRING, BOOL
 *
 * @property type Type declared by BaseType enum
 */
class BaseTypeAST(val type: BaseType) : TypeAST, AbstractAST() {

    override fun equals(other: Any?): Boolean {
        when (other) {
            is BaseTypeAST -> {
                if (type == BaseType.ANY || other.type == BaseType.ANY) {
                    return true
                }
                return type == other.type
            }
            is ArrayTypeAST -> {
                return this == other.type
            }
            is PairTypeAST -> {
                return type == BaseType.NULL
            }
            else -> return false
        }
    }

    override fun toString(): String {
        return type.name
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}

/**
 * AST node to represent an Array Type
 *
 * @property type type of the array
 * @property dimension dimension of the array
 */
class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST, Identifiable {

    override fun equals(other: Any?): Boolean {
        if (other is ArrayTypeAST && this.dimension == other.dimension) {
            return other.type.equals(type)
        }
        return false
    }

    override fun toString(): String {
        var currentType = type
        var counter = 0
        while (currentType is ArrayTypeAST) {
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

/**
 * AST node to represent a Pair Type
 *
 * @property type1 Type of first element
 * @property type2 Type of second element
 */
class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST, Identifiable {
    override fun equals(other: Any?): Boolean {
        if (other is InnerPairTypeAST) {
            return true
        } else if (other is PairTypeAST) {
            return type1 == other.type1 && type2.equals(other.type2)
        } else if (other is ArrayTypeAST) {
            return this == other.type
        } else if (other is BaseTypeAST) {
            if (other.type == BaseType.NULL) {
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

/**
 * AST node to represent a Inner Pair Type
 * Has no fields as type information is discarded
 *
 */
class InnerPairTypeAST : TypeAST {

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
