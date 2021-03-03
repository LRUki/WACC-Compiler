package wacc.backend.translate.instruction.instructionpart

interface AddressingMode {
    fun toAssembly(): String
}

class ImmediateIntMode(val offset: Int) : AddressingMode {
    override fun toAssembly(): String {
        return "=${this.offset}"
    }
}

class ImmediateLabelMode(val label: String) : AddressingMode {
    override fun toAssembly(): String {
        return "=${this.label}"
    }
}

class RegisterMode(val reg: Register) : AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg.toAssembly()}]"
    }
}

class RegisterAddrWithOffsetMode(val reg: Register, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg.toAssembly()}, #${this.offset}]${if (this.isPreIndexed) "!" else ""}"
    }
}

class RegisterAddrScaledMode(val reg1: Register, val reg2: Register, val shiftOp: ShiftType, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg1.toAssembly()}, ${this.reg2.toAssembly()}, ${shiftOp.name} #${this.offset}]${if (this.isPreIndexed) "!" else ""}"
    }
}
