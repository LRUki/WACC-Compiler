package wacc.backend.translate.instr

import wacc.backend.translate.instr.enums.Condition
import wacc.backend.translate.instr.enums.Register
import wacc.backend.translate.instr.parts.Operand

class MoveInstr(val condition: Condition, val register: Register, val operand: Operand) : Instr {
    override fun toAssembly(): String {
        return "MOV${condition.toAssembly()} ${register.toAssembly()}, ${operand.toAssembly()}"
    }
}