package wacc.backend.translate.instruction

/**
 * Top level instruction interface which all other instructions implement.
 * Has a toAssembly function which all instructions override
 */
interface Instruction {
    fun toAssembly(): String
}