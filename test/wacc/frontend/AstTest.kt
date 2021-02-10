package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Test
import wacc.frontend.visitor.BuildAstVisitor
import java.io.File

class AstTest {

    @Test
    fun `BuildAstVisitor can build AST for valid wacc files`() {
        val folder = File("wacc_examples/valid")
        actionOnFiles(folder) { file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            val tree = parser.program()

            BuildAstVisitor().visit(tree)
        }
    }
}

