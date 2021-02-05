package wacc.frontend.identifiers

import wacc.frontend.SymbolTable

class Function (private val name: String,
                private val returnType: Identifier,
                private val parameters: List<Param>,
                private val symbolTable: SymbolTable)