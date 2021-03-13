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

    fun toLabel(): String {
        return toString()
    }

    fun isBoolOrChar(): Boolean {
        return false
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitTypeAST(this)
    }

    /**
     * @return whether the type can be declared in a declare statement.
     */
    fun isConcreteType(parentType: TypeAST? = null): Boolean

}

/**
 * Technical AST node to represent an implicit type.
 * It can only exist as a type of a DeclareStatAST.
 * It will be erased and replaced by the inferred type once the checking for
 * DeclareStatAST finishes.
 */
class ImplicitTypeAST : TypeAST {

    override fun toString(): String {
        return "var"
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return false
    }

    override fun weight() {
        TODO("Not yet implemented")
    }

    /**
     * Technically, equals() should never be called on an ImplicitTypeAST
     */
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ImplicitTypeAST) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

}
