package wacc.backend.instruction

//16 visible registers
enum class Register {

    R0, R1, R2, R3,
    R4, R5, R6, R7,
    R8, R9, R10, R11, R12,

    SP,   // R13 or Stack pointer
    LR,   // R14 or Link register
    PC,   // R15 Program counter
    CPSR; // Current Program Status Register contains condition code flags, status bits, current mode bit
}
