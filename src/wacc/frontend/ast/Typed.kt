package wacc.frontend.ast

import wacc.frontend.SymbolTable

interface Typed {
    fun getRealType(table: SymbolTable): TypeAST
}

interface Identifiable