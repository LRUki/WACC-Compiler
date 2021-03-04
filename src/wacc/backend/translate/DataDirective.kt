package wacc.backend.translate

import wacc.backend.translate.instruction.DirectiveInstr
import wacc.backend.translate.instruction.Instruction

/**
 * Data directive represents
 *
 * @property stringLabels are any string labels we have in the program
 */
class DataDirective(val stringLabels: StringLabels) {

    // Adds a string literal to the data section.
    fun addStringLabel(string: String): String {
        return stringLabels.add(string)
    }

    fun getStringLabel(string: String): String {
        return stringLabels.getLabel(string)
    }


    // Translates the data section into assembly code.
    fun translate(): List<Instruction> {
        if (stringLabels.strings.size == 0) return emptyList()
        val instructions = mutableListOf<Instruction>()
        instructions.add(DirectiveInstr("data"))
        val strings = stringLabels.translateAll()
        instructions.addAll(strings)
        return instructions
    }
}
