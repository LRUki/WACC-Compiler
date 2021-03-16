package wacc.backend.translate.instruction

/**
 * Represents Directives
 *
 * @property directive Name of the directive
 */
data class DirectiveInstr(val directive: String) : Instruction {
    override fun toArm(): String {
        return ".$directive".trim()
    }

    override fun toX86(): String {
        return ".$directive".trim()
    }
}

class LTORGDirective(): Instruction {
    override fun toArm(): String {
        return ".ltorg"
    }

    override fun toX86(): String {
        return ""
    }

    override fun equals(other: Any?): Boolean {
        return other is LTORGDirective
    }
}
