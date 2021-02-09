package wacc

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.BuildAstVisitor
import wacc.frontend.CheckSyntaxVisitor
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxErrorListener
import wacc.frontend.exception.SyntaxException
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.system.exitProcess

//fun temp() {
//    val BLACK = "\033[0;30m";   // BLACK
//    val RED = "\033[0;31m";     // RED
//    val GREEN = "\033[0;32m";   // GREEN
//    val String YELLOW = "\033[0;33m";  // YELLOW
//    val String BLUE = "\033[0;34m";    // BLUE
//    val String PURPLE = "\033[0;35m";  // PURPLE
//    val CYAN = "\033[0;36m";    // CYAN
//    val WHITE = "\033[0;37m";   // WHITE
//}



fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Missing argument!")
        exitProcess(1)
    }
    val file = File(args[0])
        try {
            val ast = frontend(file.inputStream())
        } catch (e: SyntaxException) {
            System.err.println(e.message)
            val purple: String = "\u001B[30m"
            System.err.println("\u001B[41m" +purple+ file.readLines()[e.line-1])
            exitProcess(100)
        } catch (e: SemanticException) {
            System.err.println(e.message)
            val purple: String = "\u001B[30m"
            System.err.println("\u001B[41m" +purple+ file.readLines()[e.line-1])
            //System.err.println(line of code)
            exitProcess(200)
        }
}

fun frontend(inputStream: InputStream): AST {
    val program = parse(inputStream)
    checkSyntax(program)
    val ast = buildAST(program)
    checkSemantics(ast)
    return ast
}

fun parse(inputStream: InputStream): WaccParser.ProgramContext {
    val input = CharStreams.fromStream(inputStream)
    val lexer = WaccLexer(input)
    lexer.removeErrorListeners()
    lexer.addErrorListener(SyntaxErrorListener())
    val tokens = CommonTokenStream(lexer)
    val parser = WaccParser(tokens)
    parser.removeErrorListeners()
    parser.addErrorListener(SyntaxErrorListener())
    return parser.program()
}

fun checkSyntax(program: WaccParser.ProgramContext) {
    val checkSyntaxVisitor = CheckSyntaxVisitor()
    checkSyntaxVisitor.visit(program)
}

fun buildAST(program: WaccParser.ProgramContext): AST {
    return BuildAstVisitor().visit(program)
}

fun checkSemantics(ast: AST) {
    val topST = SymbolTable(null)
    ast.check(topST)
}