package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction

data class DirectiveInstr(val directive:String, val value:String=""): Instruction {
    override fun toAssembly(): String {
        return ".$directive $value"
    }
}