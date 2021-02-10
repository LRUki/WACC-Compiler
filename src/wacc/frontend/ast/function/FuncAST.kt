package wacc.frontend.ast.function

import wacc.frontend.FuncSymbolTable
import wacc.frontend.SymbolTable
import wacc.frontend.ast.*
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.exception.semanticError

class FuncAST(val type: TypeAST, val ident: IdentAST,
              val paramList: List<ParamAST>, val body: List<StatAST>) : AbstractAST(), Identifiable {

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
            semanticError("Function ${fName.get()} is already defined", ctx)
        }
        paramList.forEach { it.check(table) }
        table.add(ident.name, this)
    }

    override fun toString(): String {
        return ident.toString()
    }

}