package wacc.backend.translate.instrs

import wacc.backend.translate.*
import wacc.backend.translate.enums.Condition
import wacc.backend.translate.enums.MemoryType
import wacc.backend.translate.enums.Register
import wacc.backend.translate.utils.*

class StoreInstr(val condition: Condition, val memType: MemoryType?,
                 val mode: AddressingMode, val srcRegister: Register): Instruction {
    override fun toAssembly(): String {
        return "STR${memType?.name ?: ""} ${srcRegister.toAssembly()}, ${mode.toAssembly()}"
    }
}