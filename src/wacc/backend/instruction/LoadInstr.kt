package wacc.backend.instruction

class LoadInstr (val destRegister : Register, val memType: MemoryType?,
                 val mode : AddressingMode, val condition : Condition): Instruction{

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