package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.AddressingMode

class StoreInstr( val memType: MemoryType?,
                 val mode: AddressingMode, val srcRegister: Register) : Instruction {
    override fun toAssembly(): String {
        return "STR${memType?.name ?: ""} ${srcRegister.toAssembly()}, ${mode.toAssembly()}"
    }
}