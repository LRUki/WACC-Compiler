package wacc.backend.instruction.utils

import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.enums.Shift

interface AddressingMode

class ImmediateInt(val offset: Int): AddressingMode
class ImmediateChar(val char: Char): AddressingMode

class RegisterAddr(val reg: Register): AddressingMode

class RegisterAddrWithOffset(val reg: Register, val offset: Int, val isPreIndexed: Boolean): AddressingMode

class RegisterAddrScaled(val reg1: Register, val reg2: Register, val shiftOp: Shift, val offset: Int, val isPreIndexed: Boolean): AddressingMode



