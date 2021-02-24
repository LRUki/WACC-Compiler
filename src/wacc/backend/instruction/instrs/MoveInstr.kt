package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.utils.Operand
import wacc.backend.instruction.enums.Register

class MoveInstr(val condition: Condition, val register: Register, val operand: Operand): Instruction {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}