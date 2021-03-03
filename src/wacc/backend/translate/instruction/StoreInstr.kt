package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instrpart.Condition
import wacc.backend.translate.instruction.instrpart.MemoryType
import wacc.backend.translate.instruction.instrpart.Register
import wacc.backend.translate.instruction.instrpart.AddressingMode

class StoreInstr(val condition: Condition, val memType: MemoryType?,
                 val mode: AddressingMode, val srcRegister: Register) : Instruction {
    override fun toAssembly(): String {
        return "STR${memType?.name ?: ""} ${srcRegister.toAssembly()}, ${mode.toAssembly()}"
    }
}