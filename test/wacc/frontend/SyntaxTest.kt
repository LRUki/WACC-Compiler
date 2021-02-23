package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.AfterClass
import org.junit.Assert.assertTrue
import org.junit.BeforeClass
import org.junit.Test
import wacc.Main
import wacc.createErrorChannels
import wacc.frontend.Utils.PATH_TO_EXAMPLES
import wacc.frontend.Utils.exitCode
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxErrorListener
import wacc.frontend.exception.SyntaxException
import wacc.frontend.visitor.CheckSyntaxVisitor
import wacc.startErrorListener
import java.io.File
import java.security.Permission
import kotlin.system.exitProcess

class SyntaxTest {

    companion object {
    }



    @Test
    fun antlrParsesValidPrograms() {
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) { file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(SyntaxErrorListener())
            try {
                parser.program()
            } catch (e: SyntaxException) {
                throw Error("antlr fails to parse valid file: " + file.path)
            }
        }
    }

    @Test
    fun syntaxCheckingDoesNotThrowErrorForValidPrograms() {
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) { file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(SyntaxErrorListener())
            val tree = parser.program()
            try {
                val checkSyntaxVisitor = CheckSyntaxVisitor()
                checkSyntaxVisitor.visit(tree)
            } catch (e: SyntaxException) {
                throw Error("syntax error on valid file: " + file.path)
            }
        }
    }


    @Test
    fun syntaxCheckingThrowsErrorForInvalidPrograms() {
        actionOnFiles(File(PATH_TO_EXAMPLES + "invalid/syntaxErr")) { file ->
            runBlocking {
                val input = CharStreams.fromStream(file.inputStream())
                val lexer = WaccLexer(input)
                val tokens = CommonTokenStream(lexer)
                val parser = WaccParser(tokens)
                parser.removeErrorListeners()
                parser.addErrorListener(SyntaxErrorListener())
                createErrorChannels()
                val job = startErrorListenerWithoutExit(Main.syntaxErrorChannel)
                val tree = parser.program()
                val checkSyntaxVisitor = CheckSyntaxVisitor()
                checkSyntaxVisitor.visit(tree)
                Main.syntaxErrorChannel.close()
                job.join()
                if (exitCode != 100) {
                    throw Error("failed to detect invalid file: " + file.path)
                }
            }
        }
    }
}
