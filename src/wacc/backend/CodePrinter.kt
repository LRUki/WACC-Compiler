package wacc.backend

import wacc.backend.translate.instruction.Instruction

const val DOT_DATA = ".data"
const val DOT_TEXT = ".text"
const val DOT_GLOBAL_MAIN = ".global main"

fun printCode(instrs: List<Instruction>): String {
    val lines = instrs.map { instr -> instr.toAssembly() }
            .map { line -> if (shouldIndent(line)) "\t" + line else line }
    val builder = StringBuilder()
    lines.forEach {
        val addEmptyLine = it.startsWith(DOT_DATA) || it.startsWith(DOT_TEXT)
        if (addEmptyLine && builder.isNotBlank()) {
            builder.appendLine()
        }
        builder.appendLine(it)
        if (addEmptyLine) {
            builder.appendLine()
        }
    }
    return builder.toString()
}

fun shouldIndent(line: String): Boolean {
    return when {
        line.isBlank() ||
                line[line.lastIndex] == ':' ||
                line.startsWith(DOT_DATA) ||
                line.startsWith(DOT_TEXT) ||
                line.startsWith(DOT_GLOBAL_MAIN) -> false
        else -> true
    }
}
