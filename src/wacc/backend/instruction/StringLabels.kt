package wacc.backend.instruction

import sun.jvm.hotspot.oops.OopUtilities.escapeString


class StringLiterals (val strings: MutableList<String>) {

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
            instructions.add(LabelInstruction("msg_$index:"))
            instructions.add(LabelInstruction("\t .word ${string.length}"))

            // display escaped characters in full
            val newString = escapeString(string)
            instructions.add(LabelInstruction("\t .ascii \"$newString\""))
        }
        return instructions
    }
}