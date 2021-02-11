package wacc.frontend.ast.type

import wacc.frontend.SymbolTable

/**
 * Implemented by any AST nodes that can have a type
 */
interface Typed {
    /**
     * Get real type
     *
     * @param table Symbol table to search in for the type
     * @return TypeAST corresponding to the underlying type
     */
    fun getRealType(table: SymbolTable): TypeAST
}

/**
 * Implemented by AST nodes which can be mapped to by the Symbol Table
 */
interface Identifiable