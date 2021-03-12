package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.StructAssignAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST
import wacc.frontend.ast.type.StructTypeAST
import wacc.frontend.ast.type.TypeAST

class StructAccessAST(val structIdent: IdentAST, val fieldIdent: IdentAST) : ExprAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val structFromIdent = table.lookup(structIdent.name)
        if (structFromIdent.isEmpty) {
            throw RuntimeException("Setting fields for a non-declared struct")
        }
        val structDeclareInST = structFromIdent.get() as DeclareStatAST
        val structName = (structDeclareInST.type as StructTypeAST).ident.name
        val structInST = table.lookupAll(structName)
        if (structInST.isEmpty || structInST.get() !is StructDeclareAST) {
            throw RuntimeException("Declared instance of non-existing type struct $structName")
        }
        val structDeclare = structInST.get() as StructDeclareAST
        var indexInStruct = 0
        for (i in structDeclare.fields.indices) {
            if (structDeclare.fields[i].ident.equals(fieldIdent)) {
                indexInStruct = i
                break
            }
        }
        val structAssignments = (structDeclareInST.rhs as StructAssignAST).assignments
        val fieldType = structAssignments[indexInStruct].getRealType(table)
        return fieldType
    }

}