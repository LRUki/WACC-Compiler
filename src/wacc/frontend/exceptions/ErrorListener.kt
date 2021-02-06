package wacc.frontend.exceptions

import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.BaseErrorListener

class ErrorListener : BaseErrorListener() {
    val exceptionGroup: MutableList<AntlrException> = mutableListOf()

    override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any?,
                             line: Int, charPositionInLine: Int,
                             msg: String?, e: RecognitionException?) {
        exceptionGroup += AntlrException("$msg at: $line,$charPositionInLine")
    }

}