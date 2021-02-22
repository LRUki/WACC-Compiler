package wacc.frontend.ast.program

import wacc.backend.instruction.*
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

    override fun check(table: SymbolTable): Boolean {
        funcList.forEach { it.checkNameAndAddToST(table) }
        funcList.forEach { it.check(table) }
        stats.forEach { it.check(table) }
        return true
    }

    override fun translate(): List<Instruction> {
//      Translate function definitions
        val functionInstructions = mutableListOf<Instruction>()
        funcList.forEach { functionInstructions.addAll(it.translate()) }

        val mainInstructions = mutableListOf<Instruction>()
        //add some stuff here. directives, .globalMain, .data,.text

        mainInstructions.add(FunctionLabel("main"))
        // AI: PUSH {lr}
        mainInstructions.add(PushInstr(listOf(Register.LR)))

        // Visit main program and add to instruction list
        stats.forEach { mainInstructions.addAll(it.translate()) }

        // AI: LDR r0, =0
        mainInstructions.add(LoadInstr(Register.R0, null, Immediate(0), Condition.AL))
        // AI: POP {pc}
        mainInstructions.add(PopInstr(listOf(Register.PC)))
//        mainInstructions.add(Directive(.ltorg))
        functionInstructions.addAll(mainInstructions)
        return functionInstructions
    }

}