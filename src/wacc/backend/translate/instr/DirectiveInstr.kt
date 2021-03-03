package wacc.backend.translate.instr

data class DirectiveInstr(val directive: String, val value: String = "") : Instr {
    override fun toAssembly(): String {
        return ".$directive $value".trim()
    }
}
