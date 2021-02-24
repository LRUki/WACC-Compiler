package wacc.backend.instruction

import wacc.backend.instruction.instrs.Label

class DataDirective(val stringLabels: StringLabels) {

    /**
     * Add a string literal to the data section.
     */
    fun addStringLabel(string: String): String {
        return stringLabels.add(string)
    }

    /**
     * Translate the data section into assembly code.
     */
    fun translate(): List<Instruction> {
        if (stringLabels.strings.size == 0) return emptyList()
        val instructions = mutableListOf<Instruction>()
        instructions.add(Label("data"))
        val strings = stringLabels.translateAll()
        instructions.addAll(strings)
        return instructions
    }
}
