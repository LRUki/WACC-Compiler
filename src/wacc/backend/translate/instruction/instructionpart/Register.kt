package wacc.backend.translate.instruction.instructionpart

/**
 * An enum to represent the 16 visible registers ARM has.
 *
 */
enum class Register(private val x86: String) {

    R0("rax"), R1("rdi"), // Argument or Result
    R2("rsi"), R3("rdx"), // Argument
    R4("rdi"), R5("rsi"), R6("rdx"), R7("rcx"), R8("r8"), // Callee saves
    R9("r9"), R10("r12"), R11("r13"), R12("r14"), // Callee saves

    SP("rsp"),   // R13 or Stack pointer
    LR("rbp"),   // R14 or Link register
    PC("rbp"),   // R15 Program counter
    CPSR("TODO"), // Current Program Status Register contains condition code flags, status bits, current mode bit

    NONE("");

    fun toArm(): String {
        return name.toLowerCase()
    }

    fun toX86(): String {
        return x86
    }
}
