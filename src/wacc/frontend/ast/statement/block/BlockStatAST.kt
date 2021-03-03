package wacc.frontend.ast.statement.block

import wacc.backend.translate.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.program.ProgramAST.Companion.translateScoped
import wacc.frontend.ast.statement.StatAST

/**
 * AST node to represent a BEGIN .. END block statement
 *
 * @property body List of statements inside the block
 */
class BlockStatAST(val body: List<StatAST>) : StatAST {
    lateinit var symTable: SymbolTable
    override fun check(table: SymbolTable): Boolean {
        symTable = SymbolTable(table)
        body.forEach {
            if (!it.check(symTable)) {
                return false
            }
        }
        return true
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        translateScoped(symTable, instr, body)
        return instr
    }
}