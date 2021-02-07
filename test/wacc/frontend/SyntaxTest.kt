package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.ANTLRErrorListener
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.io.PrintStream
import java.lang.Exception
import java.lang.System.setErr



class SyntaxTest {
    private val PATH_TO_EXAMPLES = "wacc_examples/"

    init {
        //overrides the print method to listen for std error and throw exception
        class DetectsStdErrPrintStream(out: PrintStream?) : PrintStream(out) {
            override fun print(s: String) {
                //TODO pass in s after overriding antlr exception
                throw Exception("syntax error")
            }
        }
        setErr(DetectsStdErrPrintStream(System.err))
    }


    @Test
    fun antlrParsesValidPrograms (){
        actionOnFiles(File(PATH_TO_EXAMPLES + "valid/")) {
            file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            try {
                parser.program()
            }catch(e:Exception){
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
            val tree = parser.program()
            try {
                val checkSyntaxVisitor = CheckSyntaxVisitor()
                checkSyntaxVisitor.visit(tree)
            }catch(e:Exception){
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
            try {
                val tree = parser.program()
                val checkSyntaxVisitor = CheckSyntaxVisitor()
                checkSyntaxVisitor.visit(tree)
                throw Error("failed to detect invalid file: " + file.path)
            }catch(e:Exception){
                assertTrue(e.message!!.contains("syntax error"))
            }
        }
    }


}

