package wacc.backend.translate.general

import wacc.backend.translate.instruction.Instruction

interface GeneralInstruction {

    fun toArmInstr(): List<Instruction>

    fun toX86Instr(): List<Instruction>
}