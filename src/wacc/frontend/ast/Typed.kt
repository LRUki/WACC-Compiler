package wacc.frontend.ast

interface Typed {
    fun getRealType() : TypeAST
}

interface Identifiable {
    fun isDeclarable() : Boolean {
        return true
    }

    fun isReturnable() : Boolean {
        return true
    }
}
