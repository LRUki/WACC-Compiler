package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.StructAssignAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST
import wacc.frontend.ast.type.StructTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

class StructAccessAST(val structIdent: IdentAST, val fieldIdent: IdentAST) : ExprAST, AbstractAST() {
    lateinit var structDeclare: StructDeclareAST
    lateinit var structIdentDeclareInST: DeclareStatAST

    override fun check(table: SymbolTable): Boolean {
        val structFromIdent = table.lookup(structIdent.name)
        structIdentDeclareInST = structFromIdent.get() as DeclareStatAST
        val structName = (structIdentDeclareInST.type as StructTypeAST).ident.name
        val structInST = table.lookupAll(structName)
        structDeclare = structInST.get() as StructDeclareAST
        for (i in structDeclare.fields.indices) {
            if (structDeclare.fields[i].ident.equals(fieldIdent)) {
                return true
            }
        }
        semanticError("Field ${fieldIdent.name} is not present in struct ${structIdent.name}", ctx)
        return false
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }

    /** Looks up the identifier in the symbol table to get the struct name
     *  Then looks up the struct name and gets list of fields
     *  Looks through list of fields to find correct index */
    override fun getRealType(table: SymbolTable): TypeAST {
        var indexInStruct = 0
        for (i in structDeclare.fields.indices) {
            if (structDeclare.fields[i].ident.equals(fieldIdent)) {
                indexInStruct = i
                break
            }
        }
        val structAssignments = (structIdentDeclareInST.rhs as StructAssignAST).assignments
        return structAssignments[indexInStruct].getRealType(table)
    }

}