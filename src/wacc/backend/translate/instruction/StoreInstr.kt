package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.AddressingMode

/**
 * Represents store instructions
 *
 * @property memType An optional memory type
 * @property mode A addressing mode which will tell us where to store.
 * @property srcRegister The register we are storing into memory
 */
class StoreInstr( val memType: MemoryType?,
                 val mode: AddressingMode, val srcRegister: Register) : Instruction {
    override fun toArm(): String {
        return "STR${memType?.name ?: ""} ${srcRegister.toArm()}, ${mode.toArm()}"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}