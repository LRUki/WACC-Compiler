package wacc.backend.translate.instruction.instructionpart

/**
 * An interface to represent the different types of
 * operand an instruction can take in as a parameter
 */
interface Operand {
    fun toArm(): String

    fun toX86(): String
}

class ImmediateIntOperand(val offset: Int) : Operand {
    override fun toArm(): String {
        return "#$offset"
    }

    override fun toX86(): String {
       return "$$offset"
    }
}

class ImmediateCharOperand(val char: Char) : Operand {
    override fun toArm(): String {
        val charStr: String = when (char) {
            0.toChar() -> return "#0" /* Special case for null terminator */
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

    override fun toX86(): String {
        return "$${char.toInt()}"
    }
}

class ImmediateBoolOperand(val bool: Boolean) : Operand {
    override fun toArm(): String {
        return "#${if (bool) 1 else 0}"
    }

    override fun toX86(): String {
        return "$${if (bool) 1 else 0}"
    }
}


class RegisterOperand(val reg: Register) : Operand {
    override fun toArm(): String {
        return reg.toArm()
    }

    override fun toX86(): String {
        return reg.toX86()
    }
}

class RegShiftRegOperand(val reg1: Register, val shiftOp: ShiftType, val reg2: Register) : Operand {
    override fun toArm(): String {
        return "${reg1.toArm()}, ${shiftOp.name} ${reg2.toArm()}"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}

class RegShiftOffsetOperand(val reg: Register, val shiftOp: ShiftType, val offset: Int) : Operand {
    override fun toArm(): String {
        return "${reg.toArm()}, ${shiftOp.name} #$offset"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}

