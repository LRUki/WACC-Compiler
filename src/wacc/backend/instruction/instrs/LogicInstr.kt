package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.utils.Operand

interface LogicInstr: Instruction {
}

enum class LogicInstrType {
    AND, EOR, ORR, BIC
}

abstract class AbstractLogicInstr(val type: LogicInstrType, val condition: Condition,
                                       val reg1: Register, val reg2: Register, val operand: Operand, val updateFlag: Boolean) : ArithmeticInstr {
    override fun toAssembly(): String {
        return "${type.name}${if (updateFlag) "S" else ""}${condition.toAssembly()} ${reg1.toAssembly()}, ${reg2.toAssembly()}, ${operand.toAssembly()}"
    }
}

class AndInstrType(condition: Condition, reg1: Register, reg2: Register,
                   operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.AND, condition, reg1, reg2, operand, updateFlag)
class XorInstrType(condition: Condition, reg1: Register, reg2: Register,
                   operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.EOR, condition, reg1, reg2, operand, updateFlag)
class OrInstrType(condition: Condition, reg1: Register, reg2: Register,
                   operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.ORR, condition, reg1, reg2, operand, updateFlag)
class BitClearInstrType(condition: Condition, reg1: Register, reg2: Register,
                   operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.BIC, condition, reg1, reg2, operand, updateFlag)
