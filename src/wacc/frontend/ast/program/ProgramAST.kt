package wacc.frontend.ast.program

import wacc.Main
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST
import java.io.File

/**
 * AST node to represent a Program
 *
 * @property funcList List of Function ASTs defined in the program
 * @property stats List of all the statements in the program
 */
class ProgramAST(val imports: List<ImportAST>, val stats: List<StatAST>, val funcList: MutableList<FuncAST>) : AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        funcList.forEach { it.checkNameAndAddToST(table) }
        addCurrentFileToImported()
        val importFuncs = mutableListOf<FuncAST>()
        imports.forEach {
            if (!it.check(table)) {
                return false
            }
            symTable.mergeFuncsWithTable(it.progAST.symTable)
            importFuncs.addAll(it.progAST.funcList)
        }
        /* Checks all the struct declarations so they can used inside functions */
        stats.filterIsInstance<StructDeclareAST>().forEach {
            if (!it.check(table)) {
                return false
            }
        }
        funcList.forEach { it.check(table) }
        /* Checks all the remaining statements */
        stats.filter { it !is StructDeclareAST }.forEach {
            if (!it.check(table)) {
                return false
            }
        }
        for (importFunc in importFuncs) {
            var contains = false
            for (func in funcList) {
                if (func.ident.name.equals(importFunc.ident.name)) {
                    contains = true
                    break
                }
            }
            if (!contains) {
                funcList.add(importFunc)
            }
        }
        return true
    }

    private fun addCurrentFileToImported() {
        val path = Main.waccFile.currentFilePath
        Main.importedFiles[path] = this
    }


    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitProgramAST(this)
    }

}