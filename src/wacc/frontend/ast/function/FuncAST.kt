package wacc.frontend.ast.function

import wacc.backend.CodeGenerator.freeAllCalleeReg
import wacc.backend.translate.*
import wacc.backend.translate.enums.Condition
import wacc.backend.translate.enums.Register
import wacc.backend.translate.instrs.*
import wacc.backend.translate.utils.ImmediateOperandInt
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
        //create a symbol table for the function and add all parameters to it
        symTable = FuncSymbolTable(table, this)
        paramList.forEach { symTable.add(it.ident.name, it) }
        body.forEach { if (!it.check(symTable)) {return false} }
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
        val instr = mutableListOf<Instruction>()
        instr.add(FunctionLabel(ident.name))
        instr.add(PushInstr(Register.LR))
        val stackOffset = symTable.getStackOffset()
        symTable.startingOffset = stackOffset
        if (stackOffset > 0) {
            instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        body.forEach { instr.addAll(it.translate()) }
        if (stackOffset > 0) {
            instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        instr.addAll(regsToPopInstrs(listOf(Register.PC)))
        instr.add(DirectiveInstr("ltorg"))
        freeAllCalleeReg()
        return instr
    }

    override fun toString(): String {
        return ident.toString()
    }

}