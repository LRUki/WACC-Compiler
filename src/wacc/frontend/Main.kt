package wacc.frontend

import antlr.BasicLexer
import antlr.BasicParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
    println("Input: ")

    val input = CharStreams.fromStream(System.`in`)
    val lexer = BasicLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = BasicParser(tokens)
    val tree = parser.prog()

    println(tree.toStringTree())
}