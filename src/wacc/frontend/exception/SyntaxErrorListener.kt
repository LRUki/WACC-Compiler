package wacc.frontend.exception

import kotlinx.coroutines.runBlocking
import org.antlr.v4.runtime.BaseErrorListener
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.Recognizer
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

        runBlocking { Main.waccFile.syntaxErrorChannel.send(SyntaxException("Syntax Error at line $line:$charPositionInLine $msg", line)) }
    }
}


