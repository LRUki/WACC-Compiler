package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.BufferedTokenStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Test
import java.lang.AssertionError

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
}