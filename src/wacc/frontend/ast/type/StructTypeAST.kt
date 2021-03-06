package wacc.frontend.ast.type

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.expression.IdentAST

class StructTypeAST(val ident: IdentAST) : TypeAST, AbstractAST() {
    companion object {
        val defStructIdent = IdentAST("")
    }

    override fun equals(other: Any?): Boolean {
        return other is StructTypeAST
    }

    override fun toString(): String {
        return "struct ${ident.name}"

    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }

    override fun toLabel(): String {
        return "struct_$ident"
    }
}

class StructFieldAST(val type: TypeAST, val ident: IdentAST) : AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        return type.check(table)

    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

}