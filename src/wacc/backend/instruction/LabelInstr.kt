package wacc.backend.instruction

open class LabelInstruction(val label : String): Instruction {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}

class Label(label: String): LabelInstruction("$label")

class FunctionLabel(functionName: String): LabelInstruction("f_$functionName")

