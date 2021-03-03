package wacc.backend.translate.instruction

data class DirectiveInstr(val directive: String, val value: String = "") : Instruction {
    override fun toAssembly(): String {
        return ".$directive $value".trim()
    }
}
