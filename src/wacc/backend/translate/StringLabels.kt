package wacc.backend.translate


import wacc.backend.translate.instrs.DirectiveInstr
import wacc.backend.translate.instrs.Label

class StringLabels(val strings: MutableList<String>) {

    /**
     * Add a string and returns its label of the format "msg_<int>".
     */
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

            // display escaped characters in full
//            val newString = escape(string)
//            instructions.add(DirectiveInstr("ascii \"$newString\""))
            instructions.add(DirectiveInstr("ascii \"${string}\""))

        }
        return instructions
    }
}