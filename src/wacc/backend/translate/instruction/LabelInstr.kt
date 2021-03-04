package wacc.backend.translate.instruction

/**
 * Label instr
 *
 * @property name
 * @constructor Create empty Label instr
 */
abstract class LabelInstr(val name: String) : Instruction {
    override fun toAssembly(): String {
        return "$name:"
    }
}

class Label(label: String) : LabelInstr(label)

class FunctionLabel(functionName: String) : LabelInstr("f_$functionName")

