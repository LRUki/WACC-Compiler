package wacc.frontend.ast.program

import wacc.backend.CodeGenerator.CLib
import wacc.backend.CodeGenerator.dataDirective
import wacc.backend.CodeGenerator.runtimeErrors
import wacc.backend.instruction.*
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.ImmediateInt
import wacc.backend.instruction.utils.ImmediateOperandInt
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.Translatable
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST

/**
 * AST node to represent a Program
 *
 * @property funcList List of Function ASTs defined in the program
 * @property stats List of all the statements in the program
 */
class ProgramAST(val funcList: List<FuncAST>, val stats: List<StatAST>) : AbstractAST(), Translatable {

    companion object {
        fun translateScoped(table: SymbolTable, instr: MutableList<Instruction>, stats: List<StatAST>) {
            val MAX_STACK_OFFSET = 1024
            val stackOffset = table.getStackOffset()
            if (stackOffset > 0) {
                var stackOffsetLeft = stackOffset
                table.startingOffset = stackOffset
                while (stackOffsetLeft > MAX_STACK_OFFSET) {
                    instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(MAX_STACK_OFFSET), shift = null))
                    stackOffsetLeft -= MAX_STACK_OFFSET
                }
                instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffsetLeft), shift = null))
            }

            // Visit the statements and add to instruction list
            stats.forEach { instr.addAll(it.translate()) }

            if (stackOffset > 0) {
                var stackOffsetLeft = stackOffset
                while (stackOffsetLeft > MAX_STACK_OFFSET) {
                    instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(MAX_STACK_OFFSET), shift = null))
                    stackOffsetLeft -= MAX_STACK_OFFSET
                }
                instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffsetLeft), shift = null))
            }
        }
    }

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        funcList.forEach { it.checkNameAndAddToST(table) }
        funcList.forEach { it.check(table) }
        stats.forEach { if (!it.check(table)) {return false} }
        return true
    }

    override fun translate(): List<Instruction> {
//      Translate function definitions
        val instr = mutableListOf<Instruction>()
        //add some stuff here. directives, .globalMain, .data,.text
//
//        addMsg()
//        // add msg here
        instr.add(DirectiveInstr("text"))
        instr.add(DirectiveInstr("global main"))
        funcList.forEach { instr.addAll(it.translate()) }

        instr.add(Label("main"))
        // AI: PUSH {lr}
        instr.add(PushInstr(Register.LR))
        translateScoped(symTable, instr, stats)
        // AI: LDR r0, =0
        instr.add(LoadInstr(Condition.AL, null, ImmediateInt(0), Register.R0))
        // AI: POP {pc}
        instr.add(PopInstr(Register.PC))
        instr.add(DirectiveInstr("ltorg"))

        val data = dataDirective.translate()
        val cLib = CLib.translate()
        val runtime = runtimeErrors.translate()
        // data + main + runtime err + clib calls
        return data + instr + runtime + cLib
    }

}