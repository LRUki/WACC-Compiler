package wacc.frontend.exceptions

import org.antlr.v4.runtime.Recognizer
import org.antlr.v4.runtime.RecognitionException
import org.antlr.v4.runtime.BaseErrorListener

class ErrorListener : BaseErrorListener() {

    override fun syntaxError(recognizer: Recognizer<*, *>?, offendingSymbol: Any?,
                             line: Int, charPositionInLine: Int,
                             msg: String?, e: RecognitionException?) {
    }

}