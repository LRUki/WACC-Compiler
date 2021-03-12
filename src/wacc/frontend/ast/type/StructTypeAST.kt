package wacc.frontend.ast.type

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST

class StructTypeAST(val ident: IdentAST) : TypeAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        return true
    }

    override fun equals(other: Any?): Boolean {
        return other is StructTypeAST
//        if (other is StructMemberTypesAST) {
//            val struct = symTable.lookupAll(ident.name)
//            if (struct.isEmpty || struct.get() !is StructDeclareAST) {
//                throw RuntimeException("Struct not present in the symbol table")
//            }
//            val listOfStructMemberTypes = (struct.get() as StructDeclareAST).fields.map { it.type }
//            return other.equals(StructMemberTypesAST(listOfStructMemberTypes))
//        }
//        if (other !is StructTypeAST) {
//            return false
//        }
//        return fieldTypes.equals(other.fieldTypes)
    }

    override fun toString(): String {
        return "["+" "+"]"
        TODO("Not yet implemented")
    }

    override fun isConcreteType(parentType: TypeAST?): Boolean {
        return true
//        return fieldTypes.isConcreteType(parentType)
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