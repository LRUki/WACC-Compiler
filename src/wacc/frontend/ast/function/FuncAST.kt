package wacc.frontend.ast.function

import wacc.frontend.FuncSymbolTable
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a Function
 *
 * @property type Return type of the function
 * @property ident Name of the function
 * @property paramList List of Parameter ASTs for the parameters of the function
 * @property body List of statements making up the function body
 */
class FuncAST(val type: TypeAST, var ident: IdentAST,
              val paramList: List<ParamAST>, val body: List<StatAST>) : AbstractAST(), Identifiable {
    override fun check(table: SymbolTable): Boolean {
        //create a symbol table for the function and add all parameters to it
        symTable = FuncSymbolTable(table, this)
        // Use the full function signature name to allow function overriding
        val newIdent = IdentAST(toLabel())
        newIdent.ctx = this.ident.ctx
        this.ident = newIdent
        paramList.forEach { symTable.add(it.ident.name, it) }
        body.forEach {
            if (!it.check(symTable)) {
                return false
            }
        }
        return true
    }

    fun checkNameAndAddToST(table: SymbolTable) {
        val fName = table.lookup(toLabel())
        if (fName.isPresent) {
            semanticError("Function ${fName.get()} is already defined", ctx)
        }
        paramList.forEach { it.check(table) }
        table.add(toLabel(), this)
    }

    override fun toString(): String {
        return ident.toString()
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitFuncAST(this)
    }

    fun toLabel(): String {
        val builder = StringBuilder()
        builder.append(ident.toString())
        if (paramList.isNotEmpty()) {
            paramList.forEach { builder.append("_" + it.type.toLabel()) }
        } else {
            builder.append("_void")
        }
        return builder.toString()
    }

}