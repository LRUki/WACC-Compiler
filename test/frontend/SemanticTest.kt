package frontend

import antlr.WaccLexer
import antlr.WaccParser
import kotlinx.coroutines.runBlocking
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Test
import wacc.*
import frontend.Utils.PATH_TO_EXAMPLES
import frontend.Utils.exitCode
import wacc.frontend.exception.SemanticException
import java.io.File

class SemanticTest {

    @Test
    fun semanticCheckingDoesNotThrowErrorForValidPrograms() {
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) { file ->
            runBlocking {
                val input = CharStreams.fromStream(file.inputStream())
                val lexer = WaccLexer(input)
                val tokens = CommonTokenStream(lexer)
                val parser = WaccParser(tokens)
                val program = parser.program()
                val waccFile = WaccFile(file)
                val job = startErrorListenerWithoutExit(waccFile.syntaxErrorChannel)
                waccFile.checkSemantics(waccFile.buildAST(program))
                waccFile.syntaxErrorChannel.close()
                job.join()
                if (exitCode != 0) {
                    throw Error("Detected error in valid file: " + file.path)
                }
            }
        }
    }


    @Test
    fun semanticCheckingThrowsErrorForInvalidPrograms() {
        runBlocking {
            actionOnFiles(File(PATH_TO_EXAMPLES + "invalid/semanticErr")) { file ->
                runBlocking {
                    val input = CharStreams.fromStream(file.inputStream())
                    val lexer = WaccLexer(input)
                    val tokens = CommonTokenStream(lexer)
                    val parser = WaccParser(tokens)
                    val program = parser.program()
                    val waccFile = WaccFile(file)
                    val job = startErrorListenerWithoutExit(waccFile.semanticErrorChannel)
                    waccFile.checkSemantics(waccFile.buildAST(program))
                    waccFile.semanticErrorChannel.close()
                    job.join()
                    if (exitCode != 200) {
                        throw Error("failed to detect invalid file: " + file.path)
                    }
                }
            }
        }
    }
}



