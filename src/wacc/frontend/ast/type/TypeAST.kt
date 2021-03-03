package wacc.frontend.ast.type


import wacc.frontend.ast.AST
import wacc.frontend.ast.AstVisitor

/**
 * Implemented by Type AST nodes
 * Implements the AST interface to be able to override the check method
 */
interface TypeAST : AST {
    /**
     * Equals method used to match the underlying types
     * Enforces all TypeASTs must override equals
     *
     * @param other Other TypeAST
     * @return Boolean representing result of equality check
     */
    override fun equals(other: Any?): Boolean

    override fun toString(): String

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitTypeAST(this)
    }

}

