package wacc

import antlr.WaccLexer
import antlr.WaccParser
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.Main.waccFile
import wacc.backend.generateCode
import wacc.backend.printCode
import wacc.extension.optimization.ConstantEvaluationVisitor
import wacc.extension.optimization.ControlFlowVisitor
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
    lateinit var waccFile: WaccFile
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        println("Missing argument!")
        exitProcess(1)
    }

    val paths = ArrayList<String>()
    val flags = ArrayList<String>()
    for (arg in args) {
        if (arg.startsWith("-")) {
            flags.add(arg)
        } else {
            paths.add(arg)
        }
    }

    val optimize = flags.contains("-o")
    val controlFlow = flags.contains("-cf")

    val inputFile = File(paths[0])
    waccFile = WaccFile(inputFile)
    waccFile.frontend()

    if (optimize) {
        waccFile.optimise()
    }
    if (controlFlow) {
        waccFile.controlFlowAnalysis()
    }

    val outputString = waccFile.backend()
    var outputFileName = inputFile.nameWithoutExtension + ".s"
    if (paths.size > 1) {
        outputFileName = paths[1]
    }
    val outputFile = File(outputFileName)

    if (paths.size > 1) {
        Files.createDirectories(outputFile.toPath().parent)
    }
    outputFile.writeText(outputString)
}

class WaccFile(val file: File) {
    lateinit var syntaxErrorChannel: Channel<SyntaxException>
    lateinit var semanticErrorChannel: Channel<SemanticException>
    var currentFilePath: String = file.absolutePath
    lateinit var ast: AST

    init {
        waccFile = this
        createErrorChannels()
    }

    fun frontend(): AST {
        runBlocking {
            var job = startErrorListener(syntaxErrorChannel, file)
            val program = parse(file.inputStream())
            checkSyntax(program)
            syntaxErrorChannel.close()
            job.join()

            ast = buildAST(program)
            job = startErrorListener(semanticErrorChannel, file)
            checkSemantics(ast)
            semanticErrorChannel.close()
            job.join()
        }
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

    fun backend(): String {
        val instrs = generateCode(ast as ProgramAST)
        return printCode(instrs)
    }

    fun optimise() {
        ast = ConstantEvaluationVisitor().visit(ast)
    }

    fun controlFlowAnalysis() {
        ast = ControlFlowVisitor().visit(ast)
    }
}