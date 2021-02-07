package wacc.frontend

import TestCompiler
import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.BufferedTokenStream
import org.antlr.v4.runtime.CharStreams
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test
import java.io.File


class ParserTest {

    @Test
    fun canParseSimpleInput() {
        val input = CharStreams.fromString("begin 1+2 end")
        val lexer = WaccLexer(input)

        val tokens = BufferedTokenStream(lexer)
        val parser = WaccParser(tokens)
        val tree = parser.program()
        assertThat(tree.getChild(0).text, `is`("begin"))
        assertThat(tree.getChild(1).text, `is`("1"))
        assertThat(tree.getChild(2).text, `is`("+"))
        assertThat(tree.getChild(3).text, `is`("2"))
        assertThat(tree.getChild(4).text, `is`("end"))
    }

    @Test
    fun canHandleMalformedPrograms() {}

    @Test
    fun doesNotRecogniseIncorrectTokens() {}
//        val input = CharStreams.fromString("begin ski end ")
//        val lexer = WaccLexer(input)
//        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("begin"))
//        try {
//            lexer.nextToken().text
//        }catch (error: AssertionError){
//            return
//        }
////        fail("Lexer recognises an token that it shouldn't")
//    }

    @Test
    fun validFilesReturnExitCode0(){
    File("wacc_examples/valid").walkTopDown().forEach {
        if (it.path.endsWith("wacc")) {
            val result = TestCompiler(it.path).testCompile()
            val exitCode = result?.exitCode
            assertTrue(exitCode == 0)
            }
        }
    }

//    @Test
//    fun filesWithSyntaxErrorReturnExitCode100() {
//    File("wacc_examples/invalid/syntaxErr").walkTopDown().forEach {
//            if (it.path.endsWith("wacc")) {
//                val result = TestCompiler(it.path).testCompile()
//                val exitCode = result?.exitCode
//                assertTrue(exitCode == 100)
//            }
//        }
//    }

//    @Test
//    fun filesWithSemanticErrorReturnExitCode200() {
//    File("wacc_examples/invalid/syntaxErr").walkTopDown().forEach {
//            if (it.path.endsWith("wacc")) {
//                val result = TestCompiler(it.path).testCompile()
//                val exitCode = result?.exitCode
//                assertTrue(exitCode == 200)
//            }
//        }
//    }


}