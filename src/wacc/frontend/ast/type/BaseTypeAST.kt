package wacc.frontend.ast.type

import wacc.frontend.ast.AbstractAST

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

    override fun isBoolOrChar(): Boolean {
        return (type == BaseType.BOOL) || (type == BaseType.CHAR)
    }

    override fun toString(): String {
        return type.name
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}

enum class BaseType(val bytes: Int) {
    INT(4), BOOL(1), CHAR(1), STRING(4)
}
