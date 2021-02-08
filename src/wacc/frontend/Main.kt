package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.ast.*
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.IntLiterAST
import java.io.File


fun main() {
//    val input = CharStreams.fromStream(System.`in`)
    val folder = File("wacc_examples/valid/IO/read/echoBigInt.wacc")
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
    val emptyRHS = IntLiterAST(0)
    topSymbolTable.add("int", DeclareStatAST(BaseTypeAST(BaseType.INT), IdentAST("int"), emptyRHS))
    topSymbolTable.add("char", DeclareStatAST(BaseTypeAST(BaseType.CHAR), IdentAST("char"), emptyRHS))
    topSymbolTable.add("bool", DeclareStatAST(BaseTypeAST(BaseType.BOOL), IdentAST("bool"), emptyRHS))
    topSymbolTable.add("string", DeclareStatAST(BaseTypeAST(BaseType.STRING), IdentAST("string"), emptyRHS))
    topSymbolTable.add("pair", DeclareStatAST(PairTypeAST(BaseTypeAST(BaseType.ANY), BaseTypeAST(BaseType.ANY)), IdentAST("pair"), emptyRHS))
    topSymbolTable.add("array", DeclareStatAST(ArrayTypeAST(BaseTypeAST(BaseType.ANY), 0), IdentAST("array"), emptyRHS))
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