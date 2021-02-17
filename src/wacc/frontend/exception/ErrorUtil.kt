package wacc.frontend.exception

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.antlr.v4.runtime.ParserRuleContext
import wacc.Main
import java.io.File

object ErrorUtil {
    val LINES_ABOVE_BELOW_ERROR = 3
}

fun syntaxError(msg: String, ctx: ParserRuleContext) {
    GlobalScope.launch {  Main.syntaxErrorChannel.send(SyntaxException(
            "Syntax Error at line" +
                    " ${ctx.start.line}:${ctx.start.charPositionInLine} $msg", ctx.start.line
    )) }
}

fun semanticError(msg: String, ctx: ParserRuleContext) {
    throw SemanticException("Semantic Error at line ${ctx.start.line}:${ctx.start.charPositionInLine} $msg", ctx.start.line
    )
}

fun printErrorLineInCode(e: Exception, file: File) {
    var lineNumber = 0
    if (e is SemanticException) {
        lineNumber = e.line
    } else if (e is SyntaxException) {
        lineNumber = e.line
    }
    val fileLines = file.readLines()
    val bold = "\u001b[1m"
    val reset = "\u001b[m"
    val green = "$bold\u001B[38;2;22;198;12m"
    val red = "$bold\u001B[38;2;187;0;0m"
    val bg = "\u001B[48:5:242m"
    val bgHighlighted = "\u001B[48:5:244m"
    System.err.println("Location of error in file: ")
    val linesEitherSide = ErrorUtil.LINES_ABOVE_BELOW_ERROR
    for (i in lineNumber-linesEitherSide-1 until lineNumber+linesEitherSide) {
        try {
            if (i == lineNumber - 1) {
                System.err.println("$bgHighlighted$red${i+1} ${fileLines[i]}$reset")
                continue
            }
            System.err.println("$bg$green${i+1} ${fileLines[i]}$reset")
        } catch (e: IndexOutOfBoundsException) {
        }
    }
}
