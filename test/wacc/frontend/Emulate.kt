package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.SyntaxErrorListener
import java.io.File

import java.lang.Exception

class CompilerResult(val exitCode: Int, val exception: Exception?)

fun emulate(fileName: String): CompilerResult? {
    var exitCode = 0
    var exception: Exception? = null
    val file = File(fileName)
    try {
        val ast = frontend(file.inputStream())
    } catch (e: SyntaxException) {
        System.err.println("Syntax Error in file: $fileName")
        exception = e
        exitCode = 100
    } catch (e: SemanticException) {
        System.err.println("Semantic Error in file: $fileName")
        exception = e
        exitCode = 200
    } catch (e: Exception) {
        System.err.println("Error in file: $fileName" + e.message)
        System.err.println("An exception was thrown that was not syntax or semantic")
        exitCode = 1
    }
    return CompilerResult(exitCode, exception)
}
