package wacc.backend.translate.instr.parts

import wacc.backend.translate.instr.enums.Register
import wacc.backend.translate.instr.enums.ShiftType

interface AddressingMode {
    fun toAssembly(): String
}

class ImmediateInt(val offset: Int) : AddressingMode {
    override fun toAssembly(): String {
        return "=${this.offset}"
    }
}

class ImmediateLabel(val label: String) : AddressingMode {
    override fun toAssembly(): String {
        return "=${this.label}"
    }
}

class RegisterAddr(val reg: Register) : AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg.toAssembly()}]"
    }
}

class RegisterAddrWithOffset(val reg: Register, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg.toAssembly()}, #${this.offset}]${if (this.isPreIndexed) "!" else ""}"
    }
}

class RegisterAddrScaled(val reg1: Register, val reg2: Register, val shiftOp: ShiftType, val offset: Int, val isPreIndexed: Boolean) : AddressingMode {
    override fun toAssembly(): String {
        return "[${this.reg1.toAssembly()}, ${this.reg2.toAssembly()}, ${shiftOp.name} #${this.offset}]${if (this.isPreIndexed) "!" else ""}"
    }
}
