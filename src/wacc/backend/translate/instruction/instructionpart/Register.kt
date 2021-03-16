package wacc.backend.translate.instruction.instructionpart

/**
 * An enum to represent the 16 visible registers ARM has.
 *
 */
enum class Register(private val x86: String) {

    R0("eax"), R1("edi"), // Argument or Result
    R2("esi"), R3("edx"), // Argument
    R4("edi"), R5("esi"), R6("rdx"), R7("ecx"), R8("e8"), // Callee saves
    R9("e9"), R10("e12"), R11("e13"), R12("r14"), // Callee saves

    SP("esp"),   // R13 or Stack pointer
    LR("ebp"),   // R14 or Link register
    PC("ebp"),   // R15 Program counter
    CPSR("TODO"), // Current Program Status Register contains condition code flags, status bits, current mode bit

    NONE("");

    fun toArm(): String {
        return name.toLowerCase()
    }

    fun toX86(): String {
        return "%$x86"
    }
}
