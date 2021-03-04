package wacc.backend.translate


import wacc.backend.translate.instruction.DirectiveInstr
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.Label

/**
 * String labels represents a list of strings in the program that
 * need to appear in the data directive
 *
 * @property strings is an initially empty list of strings
 */
class StringLabels(val strings: MutableList<String>) {


    // Adds a string to the list of strings
    // and returns its label of the format "msg_<int>".
    fun add(string: String): String {
        if (strings.contains(string)) {
            return "msg_${strings.indexOf(string)}"
        }
        strings.add(string)
        return "msg_${strings.size - 1}"
    }

    fun getLabel(string: String): String {
        val index = strings.indexOf(string)
        if (index == -1) {
            throw RuntimeException("Label has not been created")
        }
        return "msg_${strings.indexOf(string)}"
    }

    /**
     * Translates into the form
     * <string label>:
     *     .word <length of string>
     *     .ascii "<actual string literal>"
     */
    fun translateAll(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        for ((index, string) in strings.withIndex()) {
            instructions.add(Label("msg_$index"))
            instructions.add(DirectiveInstr("word ${
                string.length - string.filter { c -> c == '\\' }.count()
            }"))

            instructions.add(DirectiveInstr("ascii \"${string}\""))

        }
        return instructions
    }
}