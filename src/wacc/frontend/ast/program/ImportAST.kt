package wacc.frontend.ast.program

import wacc.Main
import wacc.WaccFile
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.exception.semanticError
import wacc.main
import java.io.File

class ImportAST(val ident: IdentAST) : AbstractAST() {
    lateinit var progAST: ProgramAST

    companion object {
        val validFileExtensions = listOf<String>("wacc")
    }


    override fun check(table: SymbolTable): Boolean {
        symTable = table
        val filename = ident.name
        //check the the file extension is correct
        var inputFile = File(filename)
        if (!validFileExtensions.contains(inputFile.extension)) {
            semanticError("Unsupported file is being imported", ctx)
            return false
        }
        //check that the file exists in the provided place
        val path = Main.waccFile.currentFilePath.substringBeforeLast(File.separator)
        inputFile = File(path, filename)
        if (!inputFile.exists()) {
            semanticError("Importing file that does not exist", ctx)
            return false
        }
        //check not importing current file
        if (Main.waccFile.currentFilePath.substringAfterLast(File.separator).equals(filename)) {
            semanticError("Trying to import the current file", ctx)
            return false
        }

        //load the imported files functions into this symbol table
        val oldWaccFile = Main.waccFile
        val newWaccFile = WaccFile(inputFile)
        Main.waccFile = newWaccFile
        newWaccFile.frontend()
        progAST = newWaccFile.ast as ProgramAST
        Main.waccFile = oldWaccFile
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

}