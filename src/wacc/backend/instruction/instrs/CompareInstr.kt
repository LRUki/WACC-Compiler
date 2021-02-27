package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.utils.Operand

class CompareInstr(val register: Register,
                   val operand: Operand): Instruction {
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
