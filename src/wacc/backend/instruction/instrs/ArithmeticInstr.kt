package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.utils.Operand
import wacc.backend.instruction.enums.Register


interface ArithmeticInstr: Instruction

class AddInstr(val condition: Condition, val reg1: Register, val reg2: Register, val operand: Operand): ArithmeticInstr

class SubInstr(val condition: Condition, val reg1: Register, val reg2: Register, val operand: Operand): ArithmeticInstr

class MultInstr(val condition: Condition, val destReg: Register, val reg1: Register, val reg2: Register): ArithmeticInstr