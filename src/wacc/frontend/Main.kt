package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.ast.AST
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.SyntaxErrorListener
import java.io.File
import java.io.InputStream
import java.lang.System.exit


fun main(args: Array<String>) {
//    if (args.size == 0) {
//        println("Missing argument!")
//        exit(1)
//    }
    val file = File("wacc_examples/invalid/syntaxErr/function/functionConditionalNoReturn.wacc")
        try {
            val ast = frontend(file.inputStream())
        } catch (e: SyntaxException) {
            System.err.println(e.message)
            exit(100)
        } catch (e: SemanticException) {
            System.err.println(e.message)
            exit(200)
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

