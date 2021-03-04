package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.Operand

/**
 * Compare instr
 *
 * @property register
 * @property operand
 * @constructor Create empty Compare instr
 */
class CompareInstr(val register: Register,
                   val operand: Operand) : Instruction {
    override fun toAssembly(): String {
        val compareInstr = "CMP ${register.toAssembly()}, ${operand.toAssembly()}"
        return compareInstr
    }

    // Compare Compare CMP{cond} <Rn>, <operand2>
    // Compare negative CMN{cond} <Rn>, <operand2>
    // Compare Compare immediate CMP <Rn>, #<immed_8>
    // Compare LowReg and LowReg, update flags CMP <Rn>, <Rm>
    // Compare LowReg and HighReg, update flags CMP <Rn>, <Rm>
    // Compare HighReg and LowReg, update flags CMP <Rn>, <Rm>
    // Compare HighReg and HighReg, update flags CMP <Rn>, <Rm>
    // Compare negative CMN <Rn>, <Rm>

}
