package wacc.frontend.ast.statement

import wacc.backend.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.Translatable

/**
 * Implemented by Statement AST nodes
 * Implements the AST interface to be able to override the check method
 */
interface StatAST : AST, Translatable

class SkipStatAST : StatAST {
    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}

/**
 * AST node to represent a sequential composition of statements
 * Used to temporary store multiple statements.
 * Flattened into a list of statements while building AST
 *
 * @property stats List of statements
 */
class MultiStatAST(val stats: List<StatAST>) : StatAST {
    override fun check(table: SymbolTable): Boolean {
        val blockST = SymbolTable(table)
        stats.forEach { it.check(blockST) }
        return true
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}
