package wacc.backend.translate.instruction

/**
 * Represents Directives
 *
 * @property directive Name of the directive
 */
data class DirectiveInstr(val directive: String) : Instruction {
    override fun toAssembly(): String {
        return ".$directive".trim()
    }
}
