package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.BufferedTokenStream
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.TokenStream
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class ParserTest {


    @Test
    fun lexicalAnalyserGivesCorrectTokens (){
        val input = CharStreams.fromString("begin skip end ")
        val lexer = WaccLexer(input)
        assertThat(lexer.nextToken().text, `is`("begin"))
        assertThat(lexer.nextToken().text, `is`("skip"))
        assertThat(lexer.nextToken().text, `is`("end"))
    }


    @Test
    fun `can parse simple expr`() {
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
}