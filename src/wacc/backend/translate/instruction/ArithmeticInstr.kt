package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.Operand

/**
 * Interface to represent arithmetic instructions
 */
interface ArithmeticInstr : Instruction

enum class ArithmeticInstrType {
    ADD,
    SUB,
    RSB
}

/**
 * Abstract Arithmetic instruction representing for ADD and Sub instructions
 *
 * @property type ADD, SUB or RSB (Reverse Subtraction)
 * @property condition A condition that needs to be satisfied to execute the instruction
 * @property reg1 Destination register
 * @property reg2 Source Register (first operand)
 * @property operand Second operand
 * @property updateFlag Boolean determining whether CPSR flags should be set
 */
abstract class AbstractArithmeticInstr(val type: ArithmeticInstrType,val condition: Condition, val reg1: Register,
                                       val reg2: Register, val operand: Operand, val updateFlag: Boolean) : ArithmeticInstr {
    override fun toArm(): String {
        return "${type.name}${if (updateFlag) "S" else ""}${condition.toArm()} ${reg1.toArm()}, ${reg2.toArm()}, ${operand.toArm()}"
    }

    override fun toX86(): String {
        return "${type.name.toLowerCase()}${condition.toX86()} ${operand.toX86()}, ${reg1.toX86()}"
    }
}

class AddInstr(condition: Condition, reg1: Register,
               reg2: Register, operand: Operand, updateFlag: Boolean = false) :
        AbstractArithmeticInstr(ArithmeticInstrType.ADD, condition, reg1, reg2, operand, updateFlag)

class SubInstr(condition: Condition, reg1: Register,
               reg2: Register, operand: Operand, updateFlag: Boolean = false) :
        AbstractArithmeticInstr(ArithmeticInstrType.SUB, condition, reg1, reg2, operand, updateFlag)

class ReverseSubInstr(condition: Condition, reg1: Register,
                      reg2: Register, operand: Operand, updateFlag: Boolean = false) :
        AbstractArithmeticInstr(ArithmeticInstrType.RSB, condition, reg1, reg2, operand, updateFlag)

/**
 * Instruction for multiplication
 *
 * @property condition Condition flag for instruction
 * @property rdLo First unique destination register
 * @property rdHi Second unique destination register
 * @property rn Register holding operand 1
 * @property rm Register holding operand 2
 * @property updateFlag Boolean determining whether CPSR flags should be set
 */
class MultInstr(val condition: Condition, val rdLo: Register, val rdHi: Register,
                val rn: Register, val rm: Register, val updateFlag: Boolean = false) : ArithmeticInstr {
    override fun toArm(): String {
        return "SMULL${if (updateFlag) "S" else ""}${condition.toArm()} ${rdLo.toArm()}, ${rdHi.toArm()}, ${rn.toArm()}, ${rm.toArm()}"
    }

    override fun toX86(): String {
        TODO("Not yet implemented")
    }
}
