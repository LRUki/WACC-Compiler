package wacc.backend.instruction

open class LabelInstruction(val label : String): Instruction {
    override fun toAssembly(): String {
        return "$label: "
    }
}

class Label(label: String): LabelInstruction("$label")

class FunctionLabel(functionName: String): LabelInstruction("f_$functionName")

