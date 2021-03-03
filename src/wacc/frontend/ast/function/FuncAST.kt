package wacc.frontend.ast.function

import wacc.backend.CodeGenerator.freeAllCalleeReg
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
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
        body.forEach {
            if (!it.check(symTable)) {
                return false
            }
        }
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
        val instrs = mutableListOf<Instruction>()
        instrs.add(FunctionLabel(ident.name))
        instrs.add(PushInstr(Register.LR))
        val stackOffset = symTable.getStackOffset()
        symTable.startingOffset = stackOffset
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        body.forEach { instrs.addAll(it.translate()) }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
        instrs.add(DirectiveInstr("ltorg"))
        freeAllCalleeReg()
        return instrs
    }

    override fun toString(): String {
        return ident.toString()
    }

}