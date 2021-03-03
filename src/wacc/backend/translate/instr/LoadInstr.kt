package wacc.backend.translate.instr

import wacc.backend.translate.instr.enums.Condition
import wacc.backend.translate.instr.enums.MemoryType
import wacc.backend.translate.instr.enums.Register
import wacc.backend.translate.instr.parts.AddressingMode

class LoadInstr(val condition: Condition, val memType: MemoryType?,
                val mode: AddressingMode, val destRegister: Register) : Instr {
    override fun toAssembly(): String {
        return "LDR${memType?.name ?: ""}${condition.toAssembly()} ${destRegister.toAssembly()}, ${mode.toAssembly()}"
    }

//    Load Word LDR{cond} <Rd>, <a_mode2>
//    Word with User mode privilege LDR{cond}T <Rd>, <a_mode2P>
//    PC as destination, branch and
//    exchange
//    LDR{cond} R15, <a_mode2P>
//    Byte LDR{cond}B <Rd>, <a_mode2>
//    Byte with User mode privilege LDR{cond}BT <Rd>, <a_mode2P>
//    Byte signed LDR{cond}SB <Rd>, <a_mode3>
//    Halfword LDR{cond}H <Rd>, <a_mode3>
//    Halfword signed LDR{cond}SH <Rd>, <a_mode3>
//    Doubleword LDR{cond}D <Rd>, <a_mode3>
}