package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.junit.Test
import java.io.File

class AstTest {

    @Test
    fun `BuildAstVisitor can build AST for valid wacc files`() {
        val folder = File("wacc_examples/valid")
        val list = actionOnFiles(folder) { file ->
            val input = CharStreams.fromStream(file.inputStream())
            val lexer = WaccLexer(input)
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            val tree = parser.program()

            val topST = SymbolTable(null)
            val visitor = BuildAstVisitor()
            visitor.visit(tree)
        }
    }

    private fun <T> actionOnFiles(file: File, action: (File) -> T): List<T> {
        var list = emptyList<T>()
        if (file.isDirectory) {
            for (subFile in file.listFiles()) {
                list += actionOnFiles(subFile, action)
            }
        } else {
            list += action(file)
        }
        return list
    }
}

