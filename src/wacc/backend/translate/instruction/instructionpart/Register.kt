package wacc.backend.translate.instruction.instructionpart

/**
 * An enum to represent the 16 visible registers ARM has.
 *
 */
enum class Register {

    R0, R1, // Argument or Result
    R2, R3, // Argument
    R4, R5, R6, R7, R8, // Callee saves
    R9, R10, R11, R12, // Callee saves

    SP,   // R13 or Stack pointer
    LR,   // R14 or Link register
    PC,   // R15 Program counter
    CPSR, // Current Program Status Register contains condition code flags, status bits, current mode bit

    NONE;

    fun toAssembly(): String {
        return name.toLowerCase()
    }
}
