package wacc.backend.translate.instruction.instructionpart

/**
 * An interface to represent the different types of
 * Addressing modes an instruction can take in as a parameter
 */
interface AddressingMode {
    fun toArm(): String

    fun toX86(): String
}

class ImmediateIntMode(val offset: Int) : AddressingMode {
    override fun toArm(): String {
        return "=${offset}"
    }

    override fun toX86(): String {
        return "$${offset}"
    }
}

class ImmediateLabelMode(val label: String) : AddressingMode {
    override fun toArm(): String {
        return "=${label}"
    }

    override fun toX86(): String {
        return "$${label}"
    }
}

class RegisterMode(val reg: Register) : AddressingMode {
    override fun toArm(): String {
        return "[${reg.toArm()}]"
    }

    override fun toX86(): String {
        return reg.toX86()
    }
}

class RegisterAddrWithOffsetMode(val reg: Register, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toArm(): String {
        return "[${reg.toArm()}${if (offset != 0) ", #${offset}" else ""}]${if (isPreIndexed) "!" else ""}"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}

class RegisterAddrScaledMode(val reg1: Register, val reg2: Register, val shiftOp: ShiftType, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toArm(): String {
        return "[${reg1.toArm()}, ${reg2.toArm()}, ${shiftOp.name} #${offset}]${if (isPreIndexed) "!" else ""}"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}
