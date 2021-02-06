
import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.exceptions.SemanticException
import wacc.frontend.exceptions.SyntaxException

import java.lang.Exception

class TestCompiler(private val fileName: String){
    class CompilerResult(val exitCode: Int, val exception: Exception?)

    fun testCompile(): CompilerResult? {
        val input = CharStreams.fromFileName(fileName)
        val lexer = WaccLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        var exitCode = 0
        var exception: Exception? = null
        val ast = try {
            //analysis
        }catch (syntax: SyntaxException) {
            System.err.println("Syntax Error in file: fileName")
            exception = syntax
            exitCode = 100
            return null
        }catch(semantic: SemanticException){
            System.err.println("Semantic Error in file: fileName")
            exception = semantic
            exitCode = 200
            return null
        } catch(e:Exception) {
            System.err.println("Error in file: fileName")
            System.err.println("An exception was thrown that was not syntax or semantic")
            exitCode = 1
        }
        println("No Errors in file: fileName")
        return CompilerResult(0, null)
    }



}