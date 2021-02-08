package wacc.frontend.ast

import wacc.frontend.SymbolTable

interface Typed {
    fun getRealType(table: SymbolTable): TypeAST
}

interface Identifiable {
    fun isDeclarable() : Boolean {
        return true
    }

    fun isReturnable() : Boolean {
        return true
    }
}
