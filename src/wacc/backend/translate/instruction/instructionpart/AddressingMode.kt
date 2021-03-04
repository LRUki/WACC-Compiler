package wacc.backend.translate.instruction.instructionpart

/**
 * An interface to represent the different types of
 * Addressing modes an instruction can take in as a parameter
 */
interface AddressingMode {
    fun toAssembly(): String
}

class ImmediateIntMode(val offset: Int) : AddressingMode {
    override fun toAssembly(): String {
        return "=${offset}"
    }
}

class ImmediateLabelMode(val label: String) : AddressingMode {
    override fun toAssembly(): String {
        return "=${label}"
    }
}

class RegisterMode(val reg: Register) : AddressingMode {
    override fun toAssembly(): String {
        return "[${reg.toAssembly()}]"
    }
}

class RegisterAddrWithOffsetMode(val reg: Register, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toAssembly(): String {

        return "[${reg.toAssembly()}${if (offset != 0) ", #${offset}" else ""}]${if (isPreIndexed) "!" else ""}"
    }
}

class RegisterAddrScaledMode(val reg1: Register, val reg2: Register, val shiftOp: ShiftType, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toAssembly(): String {
        return "[${reg1.toAssembly()}, ${reg2.toAssembly()}, ${shiftOp.name} #${offset}]${if (isPreIndexed) "!" else ""}"
    }
}
