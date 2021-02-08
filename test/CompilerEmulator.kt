
import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.BuildAstVisitor
import wacc.frontend.CheckSyntaxVisitor
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.SyntaxErrorListener

import java.lang.Exception

class CompilerEmulator(private val fileName: String){
    class CompilerResult(val exitCode: Int, val exception: Exception?)

    fun emulate(): CompilerResult? {
        val input = CharStreams.fromFileName(fileName)
        val lexer = WaccLexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(SyntaxErrorListener())
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(SyntaxErrorListener())
        val visitor = BuildAstVisitor()
        var exitCode = 0
        var exception: Exception? = null
        try {
            val tree = parser.program()
            val checkSyntaxVisitor = CheckSyntaxVisitor()
            checkSyntaxVisitor.visit(tree)
            visitor.visit(tree)
        } catch(e: SyntaxException) {
            System.err.println("Syntax Error in file: $fileName")
            exception = e
            exitCode = 100
        } catch(e: SemanticException){
            System.err.println("Semantic Error in file: $fileName")
            exception = e
            exitCode = 200
        } catch(e:Exception) {
            System.err.println("Error in file: $fileName" + e.message)
            System.err.println("An exception was thrown that was not syntax or semantic")
            exitCode = 1
        }
        return CompilerResult(exitCode, exception)
    }



}