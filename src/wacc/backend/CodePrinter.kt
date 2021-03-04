package wacc.backend

import wacc.backend.translate.instruction.Instruction

fun printCode(instrs: List<Instruction>): String {
    val lines = instrs.map { instr -> instr.toAssembly() }
            .map { line -> if (shouldIndent(line)) "\t" + line else line }
    val builder = StringBuilder()
    lines.forEach {
        val addEmptyLine = it.startsWith(".text") || it.startsWith(".data")
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
                line.startsWith(".text") ||
                line.startsWith(".data") ||
                line.startsWith(".global main") -> false
        else -> true
    }
}
