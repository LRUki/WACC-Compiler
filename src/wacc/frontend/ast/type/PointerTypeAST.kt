package wacc.frontend.ast.type

/**
 * A TypeAST representing the type of pointers.
 */
class PointerTypeAST(val type: TypeAST) : TypeAST, Identifiable {
    override fun equals(other: Any?): Boolean {
        if (other is AnyPairTypeAST) {
            return true // So that can assign null to a pointer
        }
        return other is PointerTypeAST && this.type == other.type
    }

    override fun toString(): String {
        return "$type*"
    }

    override fun toLabel(): String {
        return "${type.toLabel()}_pointer"
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return true
    }


    override fun hashCode(): Int {
        return type.hashCode()
    }
}