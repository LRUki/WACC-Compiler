package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction

open class LabelInst(val str: String): Instruction

data class Label(val label: String): LabelInst(label)

data class FunctionLabel(val functionName: String): LabelInst("f_$functionName")

