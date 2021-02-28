package wacc.frontend.ast.function

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.*
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.ImmediateOperandInt
import wacc.backend.instruction.utils.RegisterOperand
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
        instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
        instr.addAll(regsToPopInstrs(listOf(Register.PC)))
        instr.add(DirectiveInstr("ltorg"))
        return instr
    }

    override fun toString(): String {
        return ident.toString()
    }

}