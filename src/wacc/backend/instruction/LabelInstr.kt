package wacc.backend.instruction

open class LabelInst(val str: String): Instruction

data class Label(val label: String): LabelInst(label)

data class FunctionLabel(val functionName: String): LabelInst("f_$functionName")

