package wacc.backend.translate.instruction.instructionpart

enum class ShiftType {
    LSL, // Logical shift left
    LSR, // Logical shift right
    ASR, // Arithmetic shift right
    ROR, // Rotate right
    RRX  // Rotate right extended
}