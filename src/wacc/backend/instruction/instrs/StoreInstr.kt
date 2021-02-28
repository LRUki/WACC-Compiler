package wacc.backend.instruction.instrs

import wacc.backend.instruction.*
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.MemoryType
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.utils.*

class StoreInstr(val srcRegister : Register, val memType: MemoryType?,
                 val mode : AddressingMode, val condition : Condition): Instruction {
    override fun toAssembly(): String {
        return "STR${memType?.name ?: ""} ${srcRegister.toAssembly()}, ${mode.toAssembly()}"
    }
}