package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.Operand

class MoveInstr(val condition: Condition, val register: Register, val operand: Operand) : Instruction {
    override fun toAssembly(): String {
        return "MOV${condition.toAssembly()} ${register.toAssembly()}, ${operand.toAssembly()}"
    }
}