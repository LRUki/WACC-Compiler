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
    try {
        ast = frontend(file.inputStream())
    } catch (e: SyntaxException) {
        System.err.println(e.message)
        printErrorLineInCode(e, file)
        exitProcess(e.errorCode)
    } catch (e: SemanticException) {
        System.err.println(e.message)
        printErrorLineInCode(e, file)
        exitProcess(e.errorCode)
    }
}

fun createErrorChannels() {
    val synErrorChannel = Channel<SyntaxException>()
    syntaxErrorChannel = synErrorChannel
    val semErrorChannel = Channel<SemanticException>()
    Main.semanticErrorChannel = semErrorChannel
}

fun startErrorListener(errorChannel: Channel<SyntaxException>): Job {
    val job = GlobalScope.launch {
        val allErrors = mutableListOf<SyntaxException>()
        for (error in errorChannel) {
            allErrors.add(error)
        }
        var count = 0
        allErrors.forEach { System.err.println("${count++} $it") }
        if (allErrors.size > 0) {
            exitProcess(allErrors[0].errorCode)
        }
    }
    return job
}

suspend fun frontend(inputStream: InputStream): AST {
    val job = startErrorListener(syntaxErrorChannel)
    val program = parse(inputStream)
    checkSyntax(program)
    syntaxErrorChannel.close()
    job.join()
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
