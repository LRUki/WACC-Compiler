package wacc.frontend

import antlr.BasicLexer
import antlr.BasicParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test

class FrontendTest {
    @Test
    fun `can parse simple expr`() {
        val input = CharStreams.fromString("1+2")
        val lexer = BasicLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = BasicParser(tokens)
        val tree = parser.prog()

        assertThat(tree.getChild(0).getChild(0).text, `is`("1"))
        assertThat(tree.getChild(1).text, `is`("+"))
        assertThat(tree.getChild(2).getChild(0).text, `is`("2"))

    }
}