package wacc.backend.instruction


interface ArithmeticInstr: Instruction

class AddInstr(val condition: Condition, val reg1: Register, val reg2: Register, val operand: Operand): ArithmeticInstr {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}

class SubInstr(val condition: Condition, val reg1: Register, val reg2: Register, val operand: Operand): ArithmeticInstr {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}

class MultInstr(val condition: Condition, val destReg:Register, val reg1: Register, val reg2: Register): ArithmeticInstr {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}