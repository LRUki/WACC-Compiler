package wacc.backend.instruction

import wacc.frontend.SymbolTable

interface Instruction {
    fun toAssembly():String
}