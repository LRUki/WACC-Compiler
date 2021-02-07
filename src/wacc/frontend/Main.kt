package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.exceptions.ErrorListener
import java.io.File

fun main() {
    val errorListener = ErrorListener()
//    val input = CharStreams.fromStream(System.`in`)
    val folder = File("wacc_examples/valid/advanced/hashTable.wacc")
    val list = actionOnFiles(folder) { file ->
        println(file.path)
        val input = CharStreams.fromStream(file.inputStream())
        val lexer = WaccLexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(errorListener)
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(errorListener)
        val tree = parser.program()
        val checkSyntaxVisitor = CheckSyntaxVisitor()
        checkSyntaxVisitor.visit(tree)
        val visitor = BuildAstVisitor()
        val ast = visitor.visit(tree)
        ast
    }

    println()
}

fun <T> actionOnFiles(file: File, action: (File) -> T): List<T> {
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