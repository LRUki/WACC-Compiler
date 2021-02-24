package wacc.backend.instruction


interface ArithmeticInstr : Instruction

enum class ArithmeticInstrType {
    ADD,
    SUB,
    MUL
}

abstract class AbstractArithmeticInstr(val type: ArithmeticInstrType, val condition: Condition,
                                       val reg1: Register, val reg2: Register, val operand: Operand) : ArithmeticInstr {
    override fun toAssembly(): String {
        return "${type.name} ${reg1.toAssembly()}, ${reg2.toAssembly()}, ${operand.toAssembly()}"
    }
}

class AddInstr(condition: Condition, reg1: Register, reg2: Register,
               operand: Operand) : AbstractArithmeticInstr(ArithmeticInstrType.ADD, condition, reg1, reg2, operand)

class SubInstr(condition: Condition, reg1: Register, reg2: Register,
               operand: Operand) : AbstractArithmeticInstr(ArithmeticInstrType.SUB, condition, reg1, reg2, operand)

class MultInstr(condition: Condition, reg1: Register, reg2: Register,
                operand: Operand) : AbstractArithmeticInstr(ArithmeticInstrType.MUL, condition, reg1, reg2, operand)
