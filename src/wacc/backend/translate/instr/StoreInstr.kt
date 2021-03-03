package wacc.backend.translate.instr

import wacc.backend.translate.instr.enums.Condition
import wacc.backend.translate.instr.enums.MemoryType
import wacc.backend.translate.instr.enums.Register
import wacc.backend.translate.instr.parts.AddressingMode

class StoreInstr(val condition: Condition, val memType: MemoryType?,
                 val mode: AddressingMode, val srcRegister: Register) : Instr {
    override fun toAssembly(): String {
        return "STR${memType?.name ?: ""} ${srcRegister.toAssembly()}, ${mode.toAssembly()}"
    }
}