package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File

fun main() {
    println("Input: ")

//    val input = CharStreams.fromStream(System.`in`)
    val input = CharStreams.fromStream(File("wacc_examples/valid/expressions/intExpr1.wacc").inputStream())
    val lexer = WaccLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = WaccParser(tokens)
    val tree = parser.program()

//    println(tree.toStringTree())

    val visitor = BuildAstVisitor()
    val AstRoot = visitor.visit(tree)

}