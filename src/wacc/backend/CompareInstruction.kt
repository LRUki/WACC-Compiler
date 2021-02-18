package wacc.backend

class CompareInstruction(private val condition: Condition?, private val register: Register,
                         private val regToCompare: Register?,private val immediate: Int?) {

   // Compare Compare CMP{cond} <Rn>, <operand2>
   // Compare negative CMN{cond} <Rn>, <operand2>
   // Compare Compare immediate CMP <Rn>, #<immed_8>
   // Compare LowReg and LowReg, update flags CMP <Rn>, <Rm>
   // Compare LowReg and HighReg, update flags CMP <Rn>, <Rm>
   // Compare HighReg and LowReg, update flags CMP <Rn>, <Rm>
   // Compare HighReg and HighReg, update flags CMP <Rn>, <Rm>
   // Compare negative CMN <Rn>, <Rm>

}