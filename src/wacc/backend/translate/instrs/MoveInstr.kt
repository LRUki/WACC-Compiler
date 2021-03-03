package wacc.backend.translate.instrs

import wacc.backend.translate.Instruction
import wacc.backend.translate.enums.Condition
import wacc.backend.translate.enums.Register
import wacc.backend.translate.utils.Operand

class MoveInstr(val condition: Condition, val register: Register, val operand: Operand) : Instruction {
    override fun toAssembly(): String {
        return "MOV${condition.toAssembly()} ${register.toAssembly()}, ${operand.toAssembly()}"
    }
}