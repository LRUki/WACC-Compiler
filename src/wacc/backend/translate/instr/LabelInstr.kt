package wacc.backend.translate.instr

abstract class LabelInstr(val name: String) : Instr {
    override fun toAssembly(): String {
        return "$name:"
    }
}

class Label(label: String) : LabelInstr(label)

class FunctionLabel(functionName: String) : LabelInstr("f_$functionName")

