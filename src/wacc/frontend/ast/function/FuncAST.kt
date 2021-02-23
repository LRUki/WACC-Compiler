package wacc.frontend.ast.function

import wacc.backend.instruction.*
import wacc.frontend.FuncSymbolTable
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.Translatable
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
class FuncAST(val type: TypeAST, val ident: IdentAST,
              val paramList: List<ParamAST>, val body: List<StatAST>) : AbstractAST(), Identifiable, Translatable {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        //create a symbol table for the function and add all parameters to it
        val funScopeST = FuncSymbolTable(table, this)
        paramList.forEach { funScopeST.add(it.ident.name, it) }
        body.forEach { if (!it.check(funScopeST)) {return false} }
        return true
    }

    fun checkNameAndAddToST(table: SymbolTable) {
        val fName = table.lookup(ident.name)
        if (fName.isPresent) {
            semanticError("Function ${fName.get()} is already defined", ctx)
        }
        paramList.forEach { it.check(table) }
        table.add(ident.name, this)
    }

    override fun translate(): List<Instruction> {
        val functionInstructions = mutableListOf<Instruction>()
        functionInstructions.add(FunctionLabel(ident.name))
        functionInstructions.add(PushInstr(listOf(Register.LR)))

        body.forEach { functionInstructions.addAll(it.translate()) }

        functionInstructions.add(PushInstr(listOf(Register.PC, Register.PC)))
        functionInstructions.add(Directive("ltorg"))
        return functionInstructions
    }

    override fun toString(): String {
        return ident.toString()
    }

}