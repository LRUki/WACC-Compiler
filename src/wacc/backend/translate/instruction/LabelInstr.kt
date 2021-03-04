package wacc.backend.translate.instruction

/**
 * Represents Label instructions
 *
 * @property name String representing the label
 */
abstract class LabelInstr(val name: String) : Instruction {
    override fun toAssembly(): String {
        return "$name:"
    }
}

class Label(label: String) : LabelInstr(label)

class FunctionLabel(functionName: String) : LabelInstr("f_$functionName")

