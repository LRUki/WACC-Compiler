package wacc.frontend.exception
import org.antlr.v4.runtime.*


/**
 * ANTLR builtin used to identify sytax errors in lexer.
 */
class SyntaxErrorListener : BaseErrorListener() {
    override fun syntaxError(
        recognizer: Recognizer<*, *>,
        offendingSymbol: Any?,
        line: Int,
        charPositionInLine: Int,
        msg: String,
        e: RecognitionException?
    ) {
        throw SyntaxException("syntax error: line $line:$charPositionInLine at $offendingSymbol: $msg")
    }
}
