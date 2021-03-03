package wacc.backend.translate.instrs

import wacc.backend.translate.Instruction

data class DirectiveInstr(val directive: String, val value: String = "") : Instruction {
    override fun toAssembly(): String {
        return ".$directive $value".trim()
    }
}
