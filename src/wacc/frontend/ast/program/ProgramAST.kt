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
                    instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(MAX_STACK_OFFSET)))
                    stackOffsetLeft -= MAX_STACK_OFFSET
                }
                instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffsetLeft)))
            }

            // Visit the statements and add to instruction list
            stats.forEach { instr.addAll(it.translate()) }

            if (stackOffset > 0) {
                var stackOffsetLeft = stackOffset
                while (stackOffsetLeft > MAX_STACK_OFFSET) {
                    instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(MAX_STACK_OFFSET)))
                    stackOffsetLeft -= MAX_STACK_OFFSET
                }
                instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffsetLeft)))
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
        val functionInstructions = mutableListOf<Instruction>()
        funcList.forEach { functionInstructions.addAll(it.translate()) }
        val mainInstructions = mutableListOf<Instruction>()
        //add some stuff here. directives, .globalMain, .data,.text
//
//        addMsg()
//        // add msg here
        mainInstructions.add(DirectiveInstr("text"))
        mainInstructions.add(DirectiveInstr("global main"))
        mainInstructions.add(Label("main"))
        // AI: PUSH {lr}
        mainInstructions.add(PushInstr(Register.LR))
        translateScoped(symTable, mainInstructions, stats)
        // AI: LDR r0, =0
        mainInstructions.add(LoadInstr(Register.R0, null, ImmediateInt(0), Condition.AL))
        // AI: POP {pc}
        mainInstructions.add(PopInstr(Register.PC))
        mainInstructions.add(DirectiveInstr("ltorg"))

        val data = dataDirective.translate()
        val cLib = CLib.translate()
        val runtime = runtimeErrors.translate()
        // data + function + main + runtime err + clib calls
        return data + functionInstructions + mainInstructions + runtime + cLib
    }

}