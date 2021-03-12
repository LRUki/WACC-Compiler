package wacc.frontend.ast.type

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST
import java.time.temporal.IsoFields

class StructTypeAST(val ident: IdentAST) : TypeAST, AbstractAST() {
    lateinit var fieldTypes: StructMemberTypesAST

    override fun check(table: SymbolTable): Boolean {
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (other !is StructTypeAST) {
            return false
        }
        return fieldTypes.equals(other.fieldTypes)
    }

    override fun toString(): String {
        return ""
        TODO("Not yet implemented")
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return fieldTypes.isConcreteType(parentType)
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
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