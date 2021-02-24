package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction

abstract class LabelInstr(val str: String): Instruction {
    override fun toAssembly(): String {
        return "$str:"
    }
}

class Label(label: String): LabelInstr(label)

class FunctionLabel(functionName: String): LabelInstr("f_$functionName")

