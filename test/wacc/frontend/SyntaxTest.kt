package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Assert.*
import org.junit.Test
import wacc.frontend.exception.SyntaxErrorException
import wacc.frontend.exception.SyntaxErrorListener
import java.io.File


class SyntaxTest {
    private val PATH_TO_EXAMPLES = "wacc_examples/"

    @Test
    fun antlrParsesValidPrograms (){
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) {
            file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(SyntaxErrorListener())
            try {
                parser.program()
            }catch(e:SyntaxErrorException){
                throw Error("antlr fails to parse valid file: " + file.path)
            }
        }
    }

    @Test
    fun syntaxCheckingDoesNotThrowErrorForValidPrograms (){
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) {
                file ->
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
            }catch(e:SyntaxErrorException){
                throw Error("syntax error on valid file: " + file.path)
            }
        }
    }


    @Test
    fun syntaxCheckingThrowsErrorForInvalidPrograms (){
        actionOnFiles(File(PATH_TO_EXAMPLES + "invalid/syntaxErr")) {
                file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            parser.removeErrorListeners()
            parser.addErrorListener(SyntaxErrorListener())
            try {
                val tree = parser.program()
                val checkSyntaxVisitor = CheckSyntaxVisitor()
                checkSyntaxVisitor.visit(tree)
                throw Error("failed to detect invalid file: " + file.path)
            }catch(e:SyntaxErrorException){
                assertTrue(e.message!!.contains("syntax error"))
            }
        }
    }


}

