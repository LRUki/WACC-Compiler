package wacc.backend.translate

interface Instruction {
    fun toAssembly():String
}