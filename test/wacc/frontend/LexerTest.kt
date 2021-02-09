package wacc.frontend

import antlr.WaccLexer
import org.antlr.v4.runtime.CharStreams
import org.hamcrest.CoreMatchers
import org.junit.Assert
import org.junit.Test

class LexerTest {

    @Test
    fun lexerGivesCorrectTokens (){
        val input = CharStreams.fromString("begin skip end ")
        val lexer = WaccLexer(input)
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("begin"))
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("skip"))
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("end"))
    }

    @Test
    fun lexerRemovesWhiteSpace() {
        val input = CharStreams.fromString("         begin \t skip \n end \r ")
        val lexer = WaccLexer(input)
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("begin"))
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("skip"))
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("end"))
    }

    @Test
    fun lexerRemovesComments() {
        val input = CharStreams.fromString("begin #this is a comment\n" +
                                              "skip #this is another comment\n" +
                                              "end")
        val lexer = WaccLexer(input)
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("begin"))
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("skip"))
        Assert.assertThat(lexer.nextToken().text, CoreMatchers.`is`("end"))
    }


}