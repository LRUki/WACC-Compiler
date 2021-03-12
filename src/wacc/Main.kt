package wacc

import antlr.WaccLexer
import antlr.WaccParser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.Main.semanticErrorChannel
import wacc.Main.syntaxErrorChannel
import wacc.backend.generateCode
import wacc.backend.printCode
import wacc.extension.optimization.ConstantEvaluationVisitor
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxErrorListener
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.printErrorLineInCode
import wacc.frontend.visitor.BuildAstVisitor
import wacc.frontend.visitor.CheckSyntaxVisitor
import java.io.File
import java.io.InputStream
import java.nio.file.Files
import kotlin.system.exitProcess

object Main {
    lateinit var syntaxErrorChannel: Channel<SyntaxException>
    lateinit var semanticErrorChannel: Channel<SemanticException>
}

suspend fun main(args: Array<String>) {
    var ast: AST
    if (args.isEmpty()) {
        println("Missing argument!")
        exitProcess(1)
    }

    val paths = ArrayList<String>()
    val flags = ArrayList<String>()
    for (arg in args){
        if (arg.startsWith("-")){
            flags.add(arg)
        }else{
            paths.add(arg)
        }
    }

    val optimize = flags.contains("-o")

    val inputFile = File(paths[0])
    createErrorChannels()
    ast = frontend(inputFile)

//    if (optimize) {
//        ast = ConstantEvaluationVisitor().visit(ast)
//    }
//
//    val outputString = backend(ast)
//    var outputFileName = inputFile.nameWithoutExtension + ".s"
//    if (paths.size > 1) {
//        outputFileName = paths[1]
//    }
//    val outputFile = File(outputFileName)
//
//    if (paths.size > 1) {
//        Files.createDirectories(outputFile.toPath().parent)
//    }
//    outputFile.writeText(outputString)
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
        allErrors.forEach {
            System.err.println("${count++} $it");printErrorLineInCode(it as Exception, file)
        }
        if (allErrors.size > 0) {
            when (val err = allErrors[0]) {
                is SemanticException -> exitProcess(err.errorCode)
                is SyntaxException -> exitProcess(err.errorCode)
            }
        }
    }
}

fun backend(ast: AST): String {
    val instrs = generateCode(ast as ProgramAST)
    return printCode(instrs)
}
