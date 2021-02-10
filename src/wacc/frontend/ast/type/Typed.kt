package wacc.frontend.ast.type

import wacc.frontend.SymbolTable

interface Typed {
    fun getRealType(table: SymbolTable): TypeAST
}

interface Identifiable