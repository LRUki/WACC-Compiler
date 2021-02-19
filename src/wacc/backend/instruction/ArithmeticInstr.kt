package wacc.backend.instruction


interface ArithmeticInstr {}

class AddInstr(val condition: Condition, val reg1: Register, val reg2: Register, val operand: Operand): ArithmeticInstr

class SubInstr(val condition: Condition, val reg1: Register, val reg2: Register, val operand: Operand): ArithmeticInstr

class MultInstr(val condition: Condition, val destReg:Register, val reg1: Register, val reg2: Register): ArithmeticInstr