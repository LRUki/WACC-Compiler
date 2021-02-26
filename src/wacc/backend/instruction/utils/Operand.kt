package wacc.backend.instruction.utils

import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.enums.Shift

interface Operand {
    fun toAssembly(): String
}

class ImmediateOperandInt(val offset: Int): Operand {
    override fun toAssembly(): String {
        return "#$offset"
    }
}

class ImmediateOperandChar(val char: Char): Operand {
    override fun toAssembly(): String {
        return "#'${this.char}'"
    }
}

class ImmediateOperandBool(val bool: Boolean): Operand {
    override fun toAssembly(): String {
        return "#${if (bool) 1 else 0}"
    }
}


class RegisterOperand(val reg: Register): Operand {
    override fun toAssembly(): String {
        return reg.toAssembly()
    }
}

class RegShiftRegOperand(val reg1: Register, val shiftOp: Shift, val reg2: Register): Operand {
    override fun toAssembly(): String {
        return "${reg1.toAssembly()}, ${shiftOp.name} ${reg2.toAssembly()}"
    }
}

class RegShiftOffsetOperand(val reg: Register, val shiftOp: Shift, val offset: Int): Operand {
    override fun toAssembly(): String {
        return "${reg.toAssembly()}, ${shiftOp.name} #$offset"
    }
}

