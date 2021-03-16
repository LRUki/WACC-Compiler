package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Register

/**
 * Represents pop instructions
 *
 * @property register The register we are popping
 */
class PopInstr(val register: Register) : Instruction {
    override fun toArm(): String {
        return "POP {${register.toArm()}}"
    }

    override fun toX86(): String {
        return "popq ${register.toX86()}"
    }
}

/* Makes a pop instruction for each register in a given list */
fun regsToPopInstrs(list: List<Register>): List<PopInstr> {
    return list.map { reg -> PopInstr(reg) }
}

class EndInstr(): Instruction{
    override fun toArm(): String {
        return "POP {${Register.PC.toArm()}}"
    }

    override fun toX86(): String {
        return """
            leave
              ret
        """.trimIndent()
    }
}