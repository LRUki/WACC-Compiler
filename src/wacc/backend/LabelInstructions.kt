package wacc.backend

open class LabelInstruction(val str: String): Instruction {
}

class Label(label: String): LabelInstruction(".$label")

class FunctionLabel(functionName: String): LabelInstruction("$functionName")

