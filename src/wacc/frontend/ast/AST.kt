package wacc.frontend.ast

import org.antlr.v4.runtime.ParserRuleContext
import wacc.backend.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.TypeAST

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

    fun checkAndGetType(table: SymbolTable): TypeAST? {
        return null
    }
}

interface Translatable {
    fun translate(): List<Instruction>
}

/**
 * Implemented by any AST that can throw an exception
 */
abstract class AbstractAST : AST {
    lateinit var ctx: ParserRuleContext
    lateinit var confirmedType: BaseType
}

