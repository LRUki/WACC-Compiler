package wacc.backend.translate.instr

import wacc.backend.translate.instr.enums.Register
import wacc.backend.translate.instr.parts.Operand

class CompareInstr(val register: Register,
                   val operand: Operand) : Instr {
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
