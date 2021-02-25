package wacc.backend.instruction.utils

import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.enums.Shift

interface AddressingMode {
    fun toAssembly(): String
}

class ImmediateInt(val offset: Int): AddressingMode {
    override fun toAssembly(): String {
        return "=${this.offset}"
    }
}

class ImmediateChar(val char: Char): AddressingMode {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }

}

class ImmediateLabel(val label:String): AddressingMode {
    override fun toAssembly(): String {
        return  "=${this.label}"
    }
}

class RegisterAddr(val reg: Register): AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg.toAssembly()}]"
    }
}

class RegisterAddrWithOffset(val reg: Register, val offset: Int, val isPreIndexed: Boolean): AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg.toAssembly()}, #${this.offset}]${if (this.isPreIndexed) "!" else ""}"
    }
}

class RegisterAddrScaled(val reg1: Register, val reg2: Register, val shiftOp: Shift, val offset: Int, val isPreIndexed: Boolean): AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg1.toAssembly()}, ${this.reg2.toAssembly()}, ${shiftOp.name} #${this.offset}]${if (this.isPreIndexed) "!" else ""}"
    }
}
