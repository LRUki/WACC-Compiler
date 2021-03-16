package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.AddressingMode


/**
 * Represents load instructions
 * @property condition A condition which needs to be satisfied to execute the instruction
 * @property memType An optional memory type
 * @property mode The content we are loading
 * @property destRegister The register we are loading into
 */
class LoadInstr(val condition: Condition, val memType: MemoryType?,
                val mode: AddressingMode, val destRegister: Register) : Instruction {
    override fun toArm(): String {
        return "LDR${memType?.name ?: ""}${condition.toArm()} ${destRegister.toArm()}, ${mode.toArm()}"
    }

    override fun toX86(): String {
        return "mov${condition.toX86()} ${mode.toX86()}, ${destRegister.toX86()}"
    }
}