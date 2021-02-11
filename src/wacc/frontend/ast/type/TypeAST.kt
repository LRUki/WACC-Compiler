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
    INT, BOOL, CHAR, STRING
}

/**
 * AST node representing a Base Type
 * INT, CHAR, STRING, BOOL
 *
 * @property type Type declared by BaseType enum
 */
class BaseTypeAST(val type: BaseType) : TypeAST, AbstractAST() {

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is AnyTypeAST -> true
            is BaseTypeAST -> type == other.type
            is ArrayTypeAST -> this == other.type
            else -> false
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
        return when (other) {
            is AnyTypeAST -> true
            is ArrayTypeAST -> this.dimension == other.dimension
            else -> false
        }
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
 * Technical AST node to represent any type.
 * It is used to represent the element type of an empty array [].
 * It will NOT appear in the type signature of any variable or function.
 * It has no fields since the type information is discarded.
 */
class AnyTypeAST : TypeAST {

    override fun equals(other: Any?): Boolean {
        return other is TypeAST
    }

    override fun toString(): String {
        return "any"
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
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
        return when (other) {
            is AnyTypeAST -> true
            is AnyPairTypeAST -> true
            is PairTypeAST -> type1 == other.type1 && type2 == other.type2
            is ArrayTypeAST -> this == other.type
            else -> false
        }
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
 * Technical AST node to represent a pair without types for its element.
 * It is used to represent inner pair types, e.g. Pair(pair, pair),
 * or the null literal.
 * It will NOT appear in the type signature of any variable or function.
 * It has no fields since the type information is discarded.
 */
class AnyPairTypeAST : TypeAST {

    override fun equals(other: Any?): Boolean {
        return other is PairTypeAST || other is AnyPairTypeAST
    }

    override fun toString(): String {
        return "pair"
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
