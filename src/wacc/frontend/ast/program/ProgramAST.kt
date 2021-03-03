package wacc.frontend.ast.program

import wacc.backend.CodeGenerator.CLib
import wacc.backend.CodeGenerator.dataDirective
import wacc.backend.CodeGenerator.runtimeErrors
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instrpart.Condition
import wacc.backend.translate.instruction.instrpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instrpart.ImmediateIntMode
import wacc.backend.translate.instruction.instrpart.ImmediateIntOperand
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
        fun translateScoped(table: SymbolTable, instrs: MutableList<Instruction>, stats: List<StatAST>) {
            val MAX_STACK_OFFSET = 1024
            val stackOffset = table.getStackOffset()
            if (stackOffset > 0) {
                var stackOffsetLeft = stackOffset
                table.startingOffset = stackOffset
                while (stackOffsetLeft > MAX_STACK_OFFSET) {
                    instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(MAX_STACK_OFFSET)))
                    stackOffsetLeft -= MAX_STACK_OFFSET
                }
                instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffsetLeft)))
            }

            // Visit the statements and add to instruction list
            stats.forEach { instrs.addAll(it.translate()) }

            if (stackOffset > 0) {
                var stackOffsetLeft = stackOffset
                while (stackOffsetLeft > MAX_STACK_OFFSET) {
                    instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(MAX_STACK_OFFSET)))
                    stackOffsetLeft -= MAX_STACK_OFFSET
                }
                instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffsetLeft)))
            }
        }
    }

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        funcList.forEach { it.checkNameAndAddToST(table) }
        funcList.forEach { it.check(table) }
        stats.forEach {
            if (!it.check(table)) {
                return false
            }
        }
        return true
    }

    override fun translate(): List<Instruction> {
//      Translate function definitions
        val instrs = mutableListOf<Instruction>()
        //add some stuff here. directives, .globalMain, .data,.text
//
//        addMsg()
//        // add msg here
        instrs.add(DirectiveInstr("text"))
        instrs.add(DirectiveInstr("global main"))
        funcList.forEach { instrs.addAll(it.translate()) }

        instrs.add(Label("main"))
        // AI: PUSH {lr}
        instrs.add(PushInstr(Register.LR))
        translateScoped(symTable, instrs, stats)
        // AI: LDR r0, =0
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(0), Register.R0))
        // AI: POP {pc}
        instrs.add(PopInstr(Register.PC))
        instrs.add(DirectiveInstr("ltorg"))

        val data = dataDirective.translate()
        val cLib = CLib.translate()
        val runtime = runtimeErrors.translate()
        // data + main + runtime err + clib calls
        return data + instrs + runtime + cLib
    }

}