package wacc.backend.translate.instruction

import wacc.backend.translate.instruction.instructionpart.Condition

/**
 * Represents branch instructions
 *
 * @property condition A condition that needs to be satisfied to execute the instruction
 * @property label Label to branch to
 * @property link Copies the address of next instruction into link register if set
 */
data class BranchInstr(val condition: Condition,
                  val label: LabelInstr, val link: Boolean) : Instruction {
    override fun toArm(): String {
        var instr = "B"
        if (link) { instr += "L" }
        instr += condition.toArm()
        return instr + " " + label.name
    }

    override fun toX86(): String {
        return "${if (condition == Condition.AL) "jmp" else "j${condition.toX86()}"} ${label.name}"
    }
}