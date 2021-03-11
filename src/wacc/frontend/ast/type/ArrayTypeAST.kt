package wacc.frontend.ast.type

import wacc.frontend.SymbolTable

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
            is ArrayTypeAST -> dimension == other.dimension
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

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return type.isConcreteType(this)
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + dimension
        return result
    }

    //compare dimension here
    override fun check(table: SymbolTable): Boolean {
        return type.check(table)
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

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return false
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}