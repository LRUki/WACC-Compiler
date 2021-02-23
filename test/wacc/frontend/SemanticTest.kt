package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Assert.assertTrue
import org.junit.Test
import wacc.*
import wacc.frontend.Utils.PATH_TO_EXAMPLES
import wacc.frontend.Utils.exitCode
import wacc.frontend.exception.SemanticException
import java.io.File

class SemanticTest {

    @Test
    fun semanticCheckingDoesNotThrowErrorForValidPrograms() {
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) { file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            val program = parser.program()
            try {
                checkSemantics(buildAST(program))
            } catch (e: SemanticException) {
                throw Error("Semantic error on valid file: " + file.path)
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
                    createErrorChannels()
                    val job = startErrorListenerWithoutExit(Main.semanticErrorChannel)
                    checkSemantics(buildAST(program))
                    Main.semanticErrorChannel.close()
                    job.join()
                    if (exitCode != 200) {
                        throw Error("failed to detect invalid file: " + file.path)
                    }
                }
            }
        }
    }
}


