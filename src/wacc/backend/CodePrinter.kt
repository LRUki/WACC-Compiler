package wacc.backend

import wacc.backend.translate.Instruction

fun printCode(instrs: List<Instruction>): String {
    val lines = instrs.map { instr -> instr.toAssembly() }
            .map { line -> if (shouldIndent(line)) "\t" + line else line }
    val builder = StringBuilder()
    lines.forEach { line -> builder.appendLine(line) }
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
