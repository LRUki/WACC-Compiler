package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.exception.SemanticErrorException
import wacc.frontend.exception.SyntaxErrorException
import wacc.frontend.exception.SyntaxErrorListener
import java.io.File

fun main() {

//    val input = CharStreams.fromStream(System.`in`)
//    val folder = File("wacc_examples/valid/advanced/hashTable.wacc")
    val folder = File("wacc_examples/valid")
    actionOnFiles(folder) { file ->
        println(file.path)
        val input = CharStreams.fromStream(file.inputStream())
        val lexer = WaccLexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(SyntaxErrorListener())
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(SyntaxErrorListener())

        try{
            val tree = parser.program()
            val checkSyntaxVisitor = CheckSyntaxVisitor()
            checkSyntaxVisitor.visit(tree)
            val visitor = BuildAstVisitor()
            val ast = visitor.visit(tree)
            ast
        }catch (e: SyntaxErrorException){
            System.err.println(e.message)
//            exit(100)
        }catch (e: SemanticErrorException){
            System.err.println(e.message)
//            exit(200)
        }


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