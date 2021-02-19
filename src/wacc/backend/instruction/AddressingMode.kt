package wacc.backend.instruction

interface AddressingMode

class Immediate(val offset: Int): AddressingMode

class RegisterAddr(val reg: Register): AddressingMode

class RegisterAddrWithOffset(val reg: Register, val offset: Int, val isPreIndexed: Boolean): AddressingMode

class RegisterAddrScaled(val reg1: Register, val reg2: Register, val shiftOp: Shift, val offset: Int, val isPreIndexed: Boolean): AddressingMode



