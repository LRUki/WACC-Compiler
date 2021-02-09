package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import wacc.frontend.ast.*
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.IntLiterAST
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.SyntaxErrorListener
import java.io.File


fun main() {
    var wrong = 0
    var total = 0
//    val input = CharStreams.fromStream(System.`in`)
    val folder = File("wacc_examples/valid/function/nested_functions/fibonacciFullRec.wacc")
    val list = actionOnFiles(folder) { file ->
        val input = CharStreams.fromStream(file.inputStream())
        val lexer = WaccLexer(input)
        lexer.removeErrorListeners()
        lexer.addErrorListener(SyntaxErrorListener())
        val tokens = CommonTokenStream(lexer)
        val parser = WaccParser(tokens)
        parser.removeErrorListeners()
        parser.addErrorListener(SyntaxErrorListener())
        var wasWrong = false
        try {
            val tree = parser.program()
            val checkSyntaxVisitor = CheckSyntaxVisitor()
            checkSyntaxVisitor.visit(tree)
            val visitor = BuildAstVisitor()
            val topST = createTopLevelST()
            SymbolTable.currentST = topST
            val ast = visitor.visit(tree)
            ast
        } catch (e: SyntaxException) {
            System.err.println(e.message)
//            exit(100)
        } catch (e: SemanticException) {
            System.err.println(e.message)
            wrong++
            wasWrong = true
//            exit(200)
        }
        total++
        if (wasWrong) {
            println("Wrong ${file.path}")
        }


    }

    println("Wrong $wrong")
    println("Total $total")
}

fun createTopLevelST(): SymbolTable {
    val topSymbolTable = SymbolTable(null)
    val emptyRHS = IntLiterAST(0)
    topSymbolTable.add("int", DeclareStatAST(BaseTypeAST(BaseType.INT), IdentAST("int"), emptyRHS))
    topSymbolTable.add("char", DeclareStatAST(BaseTypeAST(BaseType.CHAR), IdentAST("char"), emptyRHS))
    topSymbolTable.add("bool", DeclareStatAST(BaseTypeAST(BaseType.BOOL), IdentAST("bool"), emptyRHS))
    topSymbolTable.add("string", DeclareStatAST(BaseTypeAST(BaseType.STRING), IdentAST("string"), emptyRHS))
    topSymbolTable.add("null", DeclareStatAST(BaseTypeAST(BaseType.NULL), IdentAST("string"), emptyRHS))
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