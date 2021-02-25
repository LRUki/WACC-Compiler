package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.utils.Operand
import wacc.backend.instruction.enums.Register

interface ArithmeticInstr : Instruction

enum class ArithmeticInstrType {
    ADD,
    SUB,
    MUL
}

abstract class AbstractArithmeticInstr(val type: ArithmeticInstrType, val condition: Condition,
                                       val reg1: Register, val reg2: Register, val operand: Operand, val updateFlag: Boolean) : ArithmeticInstr {
    override fun toAssembly(): String {
        return "${type.name}${if (updateFlag) "S" else ""}${condition.toAssembly()} ${reg1.toAssembly()}, ${reg2.toAssembly()}, ${operand.toAssembly()}"
    }
}

class AddInstr(condition: Condition, reg1: Register, reg2: Register,
               operand: Operand, updateFlag: Boolean = false) : AbstractArithmeticInstr(ArithmeticInstrType.ADD, condition, reg1, reg2, operand, updateFlag)

class SubInstr(condition: Condition, reg1: Register, reg2: Register,
               operand: Operand, updateFlag: Boolean = false) : AbstractArithmeticInstr(ArithmeticInstrType.SUB, condition, reg1, reg2, operand, updateFlag)

class MultInstr(condition: Condition, reg1: Register, reg2: Register,
                operand: Operand, updateFlag: Boolean = false) : AbstractArithmeticInstr(ArithmeticInstrType.MUL, condition, reg1, reg2, operand, updateFlag)
