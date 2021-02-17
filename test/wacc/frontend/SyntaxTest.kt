package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Assert.assertTrue
import org.junit.Test
import wacc.frontend.Utils.PATH_TO_EXAMPLES
import wacc.frontend.exception.SyntaxErrorListener
import wacc.frontend.exception.SyntaxException
import wacc.frontend.visitor.CheckSyntaxVisitor
import java.io.File


class SyntaxTest {

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


//    @Test
//    fun syntaxCheckingThrowsErrorForInvalidPrograms() {
//        actionOnFiles(File(PATH_TO_EXAMPLES + "invalid/syntaxErr")) { file ->
//            val input = CharStreams.fromStream(file.inputStream())
//            val lexer = WaccLexer(input)
//            val tokens = CommonTokenStream(lexer)
//            val parser = WaccParser(tokens)
//            parser.removeErrorListeners()
//            parser.addErrorListener(SyntaxErrorListener())
//            try {
//                val tree = parser.program()
//                val checkSyntaxVisitor = CheckSyntaxVisitor()
//                checkSyntaxVisitor.visit(tree)
//                throw Error("failed to detect invalid file: " + file.path)
//            } catch (e: SyntaxException) {
//                assertTrue(e.message!!.contains("Syntax Error"))
//            }
//        }
//    }


}

