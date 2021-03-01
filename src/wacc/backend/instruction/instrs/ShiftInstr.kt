package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction

enum class ShiftType {
    LSL, // Logical shift left
    LSR, // Logical shift right
    ASR, // Arithmetic shift right
    ROR, // Rotate right
    RRX  // Rotate right extended
}

class ShiftInstr(val shiftType: ShiftType, val offset: Int): Instruction {
    override fun toAssembly(): String {
        return ", $shiftType #$offset"
    }
}