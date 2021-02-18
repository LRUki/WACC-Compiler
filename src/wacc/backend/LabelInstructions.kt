package wacc.backend

open class LabelInstruction(private val str: String): Instruction {
}

class Label(label: String): LabelInstruction(".$label")

class FunctionLabel(functionName: String): LabelInstruction("$functionName")

