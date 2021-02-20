package wacc.backend.instruction

class MoveInstruction(val condition: Condition, val register: Register, val operand: Operand): Instruction {
}