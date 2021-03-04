package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Register

/**
 * Represents push instructions
 *
 * @property register The register we are pushing
 */
class PushInstr(val register: Register) : Instruction {
    override fun toAssembly(): String {
        return "PUSH {${register.toAssembly()}}"
    }
}

/**
 *  Makes a push instruction for each register in a given list
 */
fun regsToPushInstrs(list: List<Register>): List<PushInstr> {
    return list.map { reg -> PushInstr(reg) }
}
