package wacc.backend.instruction.utils

import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.enums.Shift

interface Operand

class ImmediateOperand(val offset: Int): Operand

class RegisterOperand(val reg: Register): Operand

class RegShiftRegOperand(val reg1: Register, val shiftOp: Shift, val reg2: Register): Operand

class RegShiftOffsetOperand(val reg: Register, val shiftOp: Shift, val offset: Int): Operand

