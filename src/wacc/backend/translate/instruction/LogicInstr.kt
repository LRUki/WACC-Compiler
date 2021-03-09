package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.Operand

interface LogicInstr : Instruction {
}

/**
 *  Represents the different types of logical instructions
 */
enum class LogicInstrType {
    AND, EOR, ORR, BIC
}

/**
 * A general logic instruction interface all logic instructions implement
 * @property type The type of the logical instruction specified by the enum
 * @property condition A condition that needs to be satisfied to execute the instruction
 * @property reg1 Where we store the result
 * @property reg2 First operand
 * @property operand Second operand
 * @property updateFlag Tells us if we update the flags or not
 */
abstract class AbstractLogicInstr(val type: LogicInstrType, val condition: Condition,
                                  val reg1: Register, val reg2: Register, val operand: Operand, val updateFlag: Boolean) : LogicInstr {
    override fun toArm(): String {
        return "${type.name}${if (updateFlag) "S" else ""}${condition.toArm()} ${reg1.toArm()}, ${reg2.toArm()}, ${operand.toArm()}"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}

/* Represents an Add instruction */
class AndInstrType(condition: Condition, reg1: Register, reg2: Register,
                   operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.AND, condition, reg1, reg2, operand, updateFlag)

/* Represents an Exclusive Or instruction */
class XorInstrType(condition: Condition, reg1: Register, reg2: Register,
                   operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.EOR, condition, reg1, reg2, operand, updateFlag)

/* Represents an Or instruction */
class OrInstrType(condition: Condition, reg1: Register, reg2: Register,
                  operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.ORR, condition, reg1, reg2, operand, updateFlag)

/* Represents a Bit Clear instruction */
class BitClearInstrType(condition: Condition, reg1: Register, reg2: Register,
                        operand: Operand, updateFlag: Boolean = false) : AbstractLogicInstr(LogicInstrType.BIC, condition, reg1, reg2, operand, updateFlag)
