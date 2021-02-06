package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import wacc.frontend.identifiers.*




fun main() {
//    val input = CharStreams.fromStream(System.`in`)
    val folder = File("wacc_examples/valid/advanced/hashTable.wacc")
    val list = actionOnFiles(folder) { file ->
        println(file.path)
        val input = CharStreams.fromStream(file.inputStream())
        val lexer = WaccLexer(input)
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        val tree = parser.program()

        val topST = createTopLevelST()
        SymbolTable.currentST = topST
        val visitor = BuildAstVisitor()
        val ast = visitor.visit(tree)
        ast
    }

    println()
}

fun createTopLevelST(): SymbolTable {
    val topSymbolTable = SymbolTable(null)
    topSymbolTable.add("int", INT())
    topSymbolTable.add("char", CHAR())
    topSymbolTable.add("bool", BOOL())
    topSymbolTable.add("string", STRING())
    topSymbolTable.add("pair", PAIR())
    topSymbolTable.add("array", ARRAY(Type.NULL, 0))


    return topSymbolTable
}

fun printErr(message: String) {
    System.err.println(message)
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