package wacc.backend.translate.instruction.instructionpart

/**
 * An interface to represent the different types of
 * operand an instruction can take in as a parameter
 */
interface Operand {
    fun toAssembly(): String
}

class ImmediateIntOperand(val offset: Int) : Operand {
    override fun toAssembly(): String {
        return "#$offset"
    }
}

class ImmediateCharOperand(val char: Char) : Operand {
    override fun toAssembly(): String {
        val charStr: String = when (char) {
            0.toChar() -> return "#0"
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

class ImmediateBoolOperand(val bool: Boolean) : Operand {
    override fun toAssembly(): String {
        return "#${if (bool) 1 else 0}"
    }
}


class RegisterOperand(val reg: Register) : Operand {
    override fun toAssembly(): String {
        return reg.toAssembly()
    }
}

class RegShiftRegOperand(val reg1: Register, val shiftOp: ShiftType, val reg2: Register) : Operand {
    override fun toAssembly(): String {
        return "${reg1.toAssembly()}, ${shiftOp.name} ${reg2.toAssembly()}"
    }
}

class RegShiftOffsetOperand(val reg: Register, val shiftOp: ShiftType, val offset: Int) : Operand {
    override fun toAssembly(): String {
        return "${reg.toAssembly()}, ${shiftOp.name} #$offset"
    }
}

