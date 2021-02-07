package wacc.frontend.ast

interface Typed {
    fun getRealType() : TypeAST
}

interface Identifiable