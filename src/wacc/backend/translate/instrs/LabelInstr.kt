package wacc.backend.translate.instrs

import wacc.backend.translate.Instruction

abstract class LabelInstr(val name: String): Instruction {
    override fun toAssembly(): String {
        return "$name:"
    }
}

class Label(label: String): LabelInstr(label)

class FunctionLabel(functionName: String): LabelInstr("f_$functionName")

