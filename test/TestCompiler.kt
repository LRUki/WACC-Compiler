
import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

import java.lang.Exception

class TestCompiler(private val fileName: String){
    class CompilerResult(val exitCode: Int)

    fun testCompile(): CompilerResult {
        val input = CharStreams.fromFileName(fileName)
        val lexer = WaccLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        var exitCode = 0
        var exception: Exception? = null
        val ast = try {
            //analysis
        }catch (e: Exception) {
//            Syntax errors
//            System.err.println("Parse error in : $fileName")
//            exitCode = 100
        }
//         catchsemantic error
//            System.err.println("semantic error in: $fileName")
//            exitCode = 200
//        } catch anything else {
//            System.err.println("Fatal error: $fileName")
//            System.err.println("A non-syntactic, non-semantic exception was thrown!")
//            exitCode = 1 //  error output

        println("No Errors: $fileName")
        return CompilerResult(0)
    }



}