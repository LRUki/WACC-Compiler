package wacc.backend.translate.instruction

interface Instruction {
    fun toAssembly(): String
}