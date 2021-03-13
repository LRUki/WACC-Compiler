package wacc.frontend.ast.type

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST

class StructTypeAST(val ident: IdentAST) : TypeAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        return false //TODO
    }

    override fun weight() {
        TODO("Not yet implemented")
    }

    override fun equals(other: Any?): Boolean {
        return true
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return ""
        TODO("Not yet implemented")
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return true
        TODO("Implement properly")
    }


}

class StructFieldAST(val type: TypeAST, val ident: IdentAST) : AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        return super.check(table)
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

    override fun weight() {
        TODO("Not yet implemented")
    }

}