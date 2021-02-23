package wacc.backend.instruction

import kotlin.text.Regex.Companion.escape

class StringLabels (val strings: MutableList<String>) {

    /**
     * Add a string and returns its label of the format "msg_<int>".
     */
    fun add(string: String): String {
        strings.add(string)
        return "msg_${strings.size - 1}"
    }

    /**
     * <string label>:
     *     .word <length of string>
     *     .ascii "<actual string literal>"
     */
    fun translateAll(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        for ((index, string) in strings.withIndex()) {
            instructions.add(Label("msg_$index:"))
            instructions.add(Directive("word ${string.length}"))

            // display escaped characters in full
            val newString = escape(string)
            instructions.add(Directive("ascii \"$newString\""))
        }
        return instructions
    }
}