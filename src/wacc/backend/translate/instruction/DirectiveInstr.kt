package wacc.backend.translate.instruction

/**
 * Directive instr
 *
 * @property directive
 * @property value
 * @constructor Create empty Directive instr
 */
data class DirectiveInstr(val directive: String, val value: String = "") : Instruction {
    override fun toAssembly(): String {
        return ".$directive $value".trim()
    }
}
