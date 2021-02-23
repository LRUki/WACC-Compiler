package wacc

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxErrorListener
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.printErrorLineInCode
import wacc.frontend.visitor.BuildAstVisitor
import wacc.frontend.visitor.CheckSyntaxVisitor
import java.io.File
import java.io.InputStream
import kotlin.system.exitProcess
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import wacc.Main.semanticErrorChannel
import wacc.Main.syntaxErrorChannel

object Main {
    lateinit var syntaxErrorChannel: Channel<SyntaxException>
    lateinit var semanticErrorChannel: Channel<SemanticException>
}

suspend fun main(args: Array<String>){
    val ast: AST
    if (args.isEmpty()) {
        println("Missing argument!")
        exitProcess(1)
    }
    val file = File(args[0])
    createErrorChannels()
    ast = frontend(file)
}

fun createErrorChannels() {
    val synErrorChannel = Channel<SyntaxException>()
    syntaxErrorChannel = synErrorChannel
    val semErrorChannel = Channel<SemanticException>()
    semanticErrorChannel = semErrorChannel
}

fun <T> startErrorListener(errorChannel: Channel<T>, file: File): Job {
    return GlobalScope.launch {
        val allErrors = mutableListOf<T>()
        for (error in errorChannel) {
            allErrors.add(error)
        }
        var count = 0
        allErrors.forEach { System.err.println("${count++} $it");printErrorLineInCode(it as Exception, file)
        }
        if (allErrors.size > 0) {
            when (val err = allErrors[0]) {
                is SemanticException -> exitProcess(err.errorCode);
                is SyntaxException -> exitProcess(err.errorCode)
            }
        }
    }
}

suspend fun frontend(file: File): AST {
    var job = startErrorListener(syntaxErrorChannel, file)
    val program = parse(file.inputStream())
    checkSyntax(program)
    syntaxErrorChannel.close()
    job.join()

    val ast = buildAST(program)
    job = startErrorListener(semanticErrorChannel, file)
    checkSemantics(ast)
    semanticErrorChannel.close()
    job.join()
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
