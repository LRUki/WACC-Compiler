package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.Operand

/**
 * Represents compare instructions
 *
 * Compares the provided operands and sets the appropriate CPSR flags based on the result
 *
 * @property register First operand
 * @property operand Second operand
 */
class CompareInstr(val register: Register,
                   val operand: Operand) : Instruction {
    override fun toArm(): String {
        return "CMP ${register.toAssembly()}, ${operand.toAssembly()}"
    }

}
