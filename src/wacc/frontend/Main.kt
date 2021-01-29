package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream

fun main() {
    println("Input: ")

    val input = CharStreams.fromStream(System.`in`)
    val lexer = WaccLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = WaccParser(tokens)
    val tree = parser.program()

    println(tree.toStringTree())
}