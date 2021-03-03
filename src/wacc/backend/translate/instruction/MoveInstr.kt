package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instrpart.Condition
import wacc.backend.translate.instruction.instrpart.Register
import wacc.backend.translate.instruction.instrpart.Operand

class MoveInstr(val condition: Condition, val register: Register, val operand: Operand) : Instruction {
    override fun toAssembly(): String {
        return "MOV${condition.toAssembly()} ${register.toAssembly()}, ${operand.toAssembly()}"
    }
}