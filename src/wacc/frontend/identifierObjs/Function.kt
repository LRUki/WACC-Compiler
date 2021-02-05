package wacc.frontend.identifierObjs

import wacc.frontend.SymbolTable

class Function (private val name: String,
                private val returnType: Identifier,
                private val parameters: List<Param>,
                private val symbolTable: SymbolTable){

    fun getName(): String {
        return name
    }

    fun getReturnType(): Identifier {
        return returnType
    }

    fun getParameters(): List<Param> {
        return parameters
    }

    fun getSymbolTable(): SymbolTable {
        return symbolTable
    }
}