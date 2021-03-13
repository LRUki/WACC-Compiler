package wacc.frontend.ast

import org.antlr.v4.runtime.ParserRuleContext
import wacc.backend.translate.instruction.Instruction
import wacc.frontend.SymbolTable

/**
 * Implemented by all AST nodes
 */
interface AST {

    /**
     * Check method for checking the semantic correctness of an AST node
     * Default implementation provided for simple ASTs
     *
     * @param table Symbol Table to look up variable name/type information
     * @return Unused boolean value
     *         If the semantic check fails then a Semantic Exception would be called
     */
    fun check(table: SymbolTable): Boolean {
        return true
    }

    fun <S : T, T> accept(visitor: AstVisitor<S>): T

    fun weight():Void
}

/**
 * Implemented by any AST that can throw an exception
 */
abstract class AbstractAST : AST {
    lateinit var ctx: ParserRuleContext
    lateinit var symTable: SymbolTable
    var size = 0
}

