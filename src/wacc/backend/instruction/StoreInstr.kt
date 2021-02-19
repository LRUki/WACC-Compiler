package wacc.backend.instruction

class StoreInstr(val srcRegister : Register, val memType: MemoryType?,
                 val mode : AddressingMode, val condition : Condition){
}