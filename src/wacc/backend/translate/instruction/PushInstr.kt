package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Register

/**
 * Represents push instructions
 *
 * @property register The register we are pushing
 */
class PushInstr(val register: Register) : Instruction {
    override fun toArm(): String {
        return "PUSH {${register.toArm()}}"
    }
}

/* Makes a push instruction for each register in a given list */
fun regsToPushInstrs(list: List<Register>): List<PushInstr> {
    return list.map { reg -> PushInstr(reg) }
}
