package wacc.backend.translate.utils

import wacc.backend.translate.enums.Register
import wacc.backend.translate.enums.ShiftType

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
        val charStr: String = when (char) {
            0.toChar() -> "\\0"
            '\b' -> "\\b"
            '\t' -> "\\t"
            '\n' -> "\\n"
            12.toChar() -> "\\f"
            '\r' -> "\\r"
            '\"' -> "\\\""
            '\'' -> "\\\'"
            '\\' -> "\\\\"
            else -> char.toString()
        }
        return "#'${charStr}'"
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

class RegShiftRegOperand(val reg1: Register, val shiftOp: ShiftType, val reg2: Register): Operand {
    override fun toAssembly(): String {
        return "${reg1.toAssembly()}, ${shiftOp.name} ${reg2.toAssembly()}"
    }
}

class RegShiftOffsetOperand(val reg: Register, val shiftOp: ShiftType, val offset: Int): Operand {
    override fun toAssembly(): String {
        return "${reg.toAssembly()}, ${shiftOp.name} #$offset"
    }
}

