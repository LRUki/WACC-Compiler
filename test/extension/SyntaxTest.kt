package extension

import antlr.WaccLexer
import antlr.WaccParser
import kotlinx.coroutines.runBlocking
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Test
import wacc.Main
import wacc.createErrorChannels
import frontend.Utils.PATH_TO_EXAMPLES
import frontend.Utils.PATH_TO_EXT_TESTS
import frontend.Utils.exitCode
import frontend.actionOnFiles
import frontend.startErrorListenerWithoutExit
import wacc.frontend.exception.SyntaxErrorListener
import wacc.frontend.exception.SyntaxException
import wacc.frontend.visitor.CheckSyntaxVisitor
import java.io.File

class SyntaxTest {

    @Test
    fun antlrParsesValidPrograms() {
        actionOnFiles(File(PATH_TO_EXT_TESTS + "valid/")) { file ->
            runBlocking {
                val input = CharStreams.fromStream(file.inputStream())
                val lexer = WaccLexer(input)
                val tokens = CommonTokenStream(lexer)
                val parser = WaccParser(tokens)
                parser.removeErrorListeners()
                parser.addErrorListener(SyntaxErrorListener())
                createErrorChannels()
                val job = startErrorListenerWithoutExit(Main.syntaxErrorChannel)
                parser.program()
                Main.syntaxErrorChannel.close()
                job.join()
                if (exitCode != 0) {
                    throw Error("Detected antlr error for valid file " + file.path)
                }
            }
        }
    }

    @Test
    fun syntaxCheckingDoesNotThrowErrorForValidPrograms() {
        actionOnFiles(File(PATH_TO_EXT_TESTS + "valid/")) { file ->
            runBlocking {
                val input = CharStreams.fromStream(file.inputStream())
                val lexer = WaccLexer(input)
                val tokens = CommonTokenStream(lexer)
                val parser = WaccParser(tokens)
                parser.removeErrorListeners()
                parser.addErrorListener(SyntaxErrorListener())
                val tree = parser.program()
                createErrorChannels()
                val job = startErrorListenerWithoutExit(Main.syntaxErrorChannel)
                val checkSyntaxVisitor = CheckSyntaxVisitor()
                checkSyntaxVisitor.visit(tree)
                Main.syntaxErrorChannel.close()
                job.join()
                if (exitCode != 0) {
                    throw Error("Detected error for valid file " + file.path)
                }
            }
        }
    }


    @Test
    fun syntaxCheckingThrowsErrorForInvalidPrograms() {
        actionOnFiles(File(PATH_TO_EXT_TESTS + "invalid/syntaxErr")) { file ->
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
