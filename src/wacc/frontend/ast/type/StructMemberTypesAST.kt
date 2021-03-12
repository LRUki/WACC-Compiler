package wacc.frontend.ast.type

class StructMemberTypesAST(val fields: List<TypeAST>) : TypeAST {
    override fun equals(other: Any?): Boolean {
        if (other !is StructMemberTypesAST) {
            return false
        }
        if (other.fields.size != fields.size) {
            return false
        }
        for (i in fields.indices) {
            if (!other.fields[i].equals(fields[i])) {
                return false
            }
        }
        return true
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        TODO("Not yet implemented")
    }

}
