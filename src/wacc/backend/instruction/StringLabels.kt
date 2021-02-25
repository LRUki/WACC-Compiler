package wacc.backend.instruction


import wacc.backend.instruction.instrs.DirectiveInstr
import wacc.backend.instruction.instrs.Label
import kotlin.text.Regex.Companion.escape

class StringLabels (val strings: MutableList<String>) {

    /**
     * Add a string and returns its label of the format "msg_<int>".
     */
    fun add(string: String): String {
        if(strings.contains(string)){
            return "msg_${strings.indexOf(string)}"
        }
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
            instructions.add(DirectiveInstr("word ${string.length}"))

            // display escaped characters in full
            val newString = escape(string)
            instructions.add(DirectiveInstr("ascii \"$newString\""))
        }
        return instructions
    }
}