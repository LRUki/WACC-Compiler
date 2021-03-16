package wacc.backend

import wacc.backend.translate.instruction.DirectiveInstr
import wacc.backend.translate.instruction.Instruction

const val DOT_DATA = ".data"
const val DOT_TEXT = ".text"
const val DOT_GLOBAL_MAIN = ".global main"

/**
 * Function that is called by backend function in main
 * to turn the intermediate code representation into an
 * assembly file
 *
 * @param instrs List of Instructions in the intermediate code representation
 * @return ARM assembly instructions represented by all these instruction
 */
fun printCode(instrs: List<Instruction>, lang: Language): String {
    var lines =
            when (lang) {
                Language.ARM -> {
                    instrs.map { instr -> instr.toArm() }
                }
                Language.X86 -> {
                    instrs.map { instr -> instr.toX86() }
                }
            }
    lines = lines.map { line -> if (shouldIndent(line)) "\t" + line else line }
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

enum class Language {
    ARM,
    X86;
}


/** Specifies when we should indent a line in the assembly file */
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
