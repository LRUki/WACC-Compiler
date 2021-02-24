package wacc.backend.instruction

interface Operand {
    fun toAssembly(): String
}

class ImmediateOperand(val offset: Int): Operand {
    override fun toAssembly(): String {
        return "#$offset"
    }
}

class RegisterOperand(val reg: Register): Operand {
    override fun toAssembly(): String {
        return reg.toAssembly()
    }
}

class RegShiftRegOperand(val reg1: Register, val shiftOp: Shift, val reg2: Register): Operand {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}

class RegShiftOffsetOperand(val reg: Register, val shiftOp: Shift, val offset: Int): Operand {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}

