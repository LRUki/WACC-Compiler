package wacc.frontend.ast.program

import wacc.frontend
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.exception.semanticError
import wacc.main
import java.io.File

class ImportAST(val ident: IdentAST) : AbstractAST() {

    companion object {
        val validFileExtensions = listOf<String>("wacc")
    }


    override fun check(table: SymbolTable): Boolean {
        val filename = ident.name
//        check the the file extension is correct
        val inputFile = File(filename)
        if (!validFileExtensions.contains(inputFile.extension)) {
            semanticError("Unsupported file is being imported", ctx)
            return false
        }
//        check that the file exists in the provided place
        if (!inputFile.exists()) {
            semanticError("Importing file that does not exist", ctx)
            return false
        }
        //load the imported files functions into this symbol table
        val ast = frontend(inputFile)
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

}