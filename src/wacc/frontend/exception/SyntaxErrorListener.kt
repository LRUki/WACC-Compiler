package wacc.frontend.exception

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import org.antlr.v4.runtime.*
import wacc.Main

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

        GlobalScope.launch {Main.syntaxErrorChannel.send(SyntaxException("Syntax Error at line $line:$charPositionInLine $msg", line))}
    }
}


