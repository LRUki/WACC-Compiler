package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.utils.Operand
import wacc.backend.instruction.enums.Register

interface ArithmeticInstr : Instruction

enum class ArithmeticInstrType {
    ADD,
    SUB,
    RSB
}

abstract class AbstractArithmeticInstr(val type: ArithmeticInstrType, val condition: Condition,
                                       val reg1: Register, val reg2: Register, val operand: Operand, val updateFlag: Boolean, val shift: ShiftInstr?) : ArithmeticInstr {
    override fun toAssembly(): String {
        return "${type.name}${if (updateFlag) "S" else ""}${condition.toAssembly()} ${reg1.toAssembly()}, ${reg2.toAssembly()}, ${operand.toAssembly()}${shift?.toAssembly() ?: ""}"
    }
}

class AddInstr(condition: Condition, reg1: Register, reg2: Register,
               operand: Operand, updateFlag: Boolean = false, shift: ShiftInstr?) : AbstractArithmeticInstr(ArithmeticInstrType.ADD, condition, reg1, reg2, operand, updateFlag, shift)

class SubInstr(condition: Condition, reg1: Register, reg2: Register,
               operand: Operand, updateFlag: Boolean = false, shift: ShiftInstr?) : AbstractArithmeticInstr(ArithmeticInstrType.SUB, condition, reg1, reg2, operand, updateFlag, shift)

class ReverseSubInstr(condition: Condition, reg1: Register, reg2: Register,
                      operand: Operand, updateFlag: Boolean = false, shift: ShiftInstr?) : AbstractArithmeticInstr(ArithmeticInstrType.RSB, condition, reg1, reg2, operand, updateFlag, shift)

class MultInstr(val condition: Condition, val rdLo: Register, val rdHi: Register,
                val rn: Register, val rm: Register, val updateFlag: Boolean = false) : ArithmeticInstr {
    override fun toAssembly(): String {
        return "SMULL${if (updateFlag) "S" else ""}${condition.toAssembly()} ${rdLo.toAssembly()}, ${rdHi.toAssembly()}, ${rn.toAssembly()}, ${rm.toAssembly()}"
    }
}
