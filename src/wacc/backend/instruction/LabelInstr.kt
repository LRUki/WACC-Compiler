package wacc.backend.instruction

open class LabelInstruction(val str: String): Instruction {
}
class Directive(directive:String): LabelInstruction(".$directive")

class Label(label: String): LabelInstruction("$label")

class FunctionLabel(functionName: String): LabelInstruction("f_$functionName")

