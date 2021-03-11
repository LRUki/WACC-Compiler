package wacc.frontend.ast.type

import wacc.frontend.SymbolTable

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

    override fun toLabel(): String {
//        return "pair_${type1.toLabel()}_${type2.toLabel()}"
        return "pair"
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return type1.isConcreteType(this) && type2.isConcreteType(this)
    }

    override fun hashCode(): Int {
        var result = type1.hashCode()
        result = 31 * result + type2.hashCode()
        return result
    }

    override fun check(table: SymbolTable): Boolean {
        return type1.check(table) && type2.check(table)
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

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return parentType is PairTypeAST
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}