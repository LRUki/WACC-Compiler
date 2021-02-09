package wacc.frontend.ast.function

import wacc.frontend.FuncSymbolTable
import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.Identifiable
import wacc.frontend.ast.StatAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.IdentAST

class FuncAST(val type: TypeAST, val ident: IdentAST,
              val paramList: List<ParamAST>, val body: List<StatAST>) : AST, Identifiable {

    override fun check(table: SymbolTable): Boolean {
        //create a symbol table for the function and add all parameters to it
        val funScopeST = FuncSymbolTable(table, this)
        paramList.forEach { funScopeST.add(it.ident.name, it) }
        body.forEach { it.check(funScopeST) }
        return true
    }

    fun checkNameAndAddToST(table : SymbolTable) {
        val fName = table.lookup(ident.name)
        if (fName.isPresent) {
            semanticError("Invalid function name ${fName.get()}")
        }
        paramList.forEach { it.check(table) }
        table.add(ident.name, this)
    }

    override fun toString(): String {
        return ident.toString()
    }
}