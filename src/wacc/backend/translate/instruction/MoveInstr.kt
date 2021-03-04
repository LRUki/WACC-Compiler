package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.Operand

/**
 * Represents move instructions
 *
 * @property condition A condition which needs to be satisfied to do the instruction
 * @property register The register where we are moving the content into
 * @property operand The contents we are moving
 */
class MoveInstr(val condition: Condition, val register: Register, val operand: Operand) : Instruction {
    override fun toAssembly(): String {
        return "MOV${condition.toAssembly()} ${register.toAssembly()}, ${operand.toAssembly()}"
    }
}