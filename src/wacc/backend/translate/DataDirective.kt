package wacc.backend.translate

import wacc.backend.translate.instruction.DirectiveInstr
import wacc.backend.translate.instruction.Instruction

/**
 * Stores all directives which can go under the data directive
 *
 * @property stringLabels are any string labels we have in the program (e.g msg_0)
 */
class DataDirective(val stringLabels: StringLabels) {

    /** Adds a string literal to the data section. */
    fun addStringLabel(string: String): String {
        return stringLabels.add(string)
    }

    fun getStringLabel(string: String): String {
        return stringLabels.getLabel(string)
    }


    /** Translates the data section into assembly code */
    fun translate(): List<Instruction> {
        if (stringLabels.strings.size == 0) return emptyList()
        val instructions = mutableListOf<Instruction>()
        instructions.add(DirectiveInstr("data"))
        val strings = stringLabels.translateAll()
        instructions.addAll(strings)
        return instructions
    }
}
