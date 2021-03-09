package wacc.backend.translate.general

import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.LabelInstr

class FunctionCallInstr(val labelInstr: LabelInstr): GeneralInstruction {
    override fun toArmInstr(): List<Instruction> { //List<ArmInstruction>
        TODO("Not yet implemented")
    }

    override fun toX86Instr(): List<Instruction> { //List<X86Instruction>
        TODO("Not yet implemented")
    }
}