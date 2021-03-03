package wacc.backend.translate.instrs

import wacc.backend.translate.Instruction
import wacc.backend.translate.enums.Register
import wacc.backend.translate.utils.Operand

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
