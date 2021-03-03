package wacc.backend.translate

import wacc.backend.translate.instr.DirectiveInstr
import wacc.backend.translate.instr.Instr

class DataDirective(val stringLabels: StringLabels) {

    /**
     * Add a string literal to the data section.
     */
    fun addStringLabel(string: String): String {
        return stringLabels.add(string)
    }

    fun getStringLabel(string: String): String {
        return stringLabels.getLabel(string)
    }


    /**
     * Translate the data section into assembly code.
     */
    fun translate(): List<Instr> {
        if (stringLabels.strings.size == 0) return emptyList()
        val instructions = mutableListOf<Instr>()
        instructions.add(DirectiveInstr("data"))
        val strings = stringLabels.translateAll()
        instructions.addAll(strings)
        return instructions
    }
}
