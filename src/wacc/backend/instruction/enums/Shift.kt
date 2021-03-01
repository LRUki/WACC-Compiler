package wacc.backend.instruction.enums

import wacc.backend.instruction.Instruction

enum class ShiftType {
    LSL, // Logical shift left
    LSR, // Logical shift right
    ASR, // Arithmetic shift right
    ROR, // Rotate right
    RRX  // Rotate right extended
}
