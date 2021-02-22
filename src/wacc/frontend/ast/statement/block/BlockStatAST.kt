package wacc.frontend.ast.statement.block

import wacc.backend.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.statement.StatAST

/**
 * AST node to represent a BEGIN .. END block statement
 *
 * @property body List of statements inside the block
 */
class BlockStatAST(val body: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        val blockST = SymbolTable(table)
        body.forEach { it.check(blockST) }
        return true
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}