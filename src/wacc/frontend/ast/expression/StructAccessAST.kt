package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST
import wacc.frontend.ast.type.AnyTypeAST
import wacc.frontend.ast.type.StructTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

class StructAccessAST(val structIdent: IdentAST, val fieldIdent: IdentAST) : LhsAST, ExprAST, AbstractAST() {
    lateinit var structDeclare: StructDeclareAST

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        var structFromIdent = table.lookup(structIdent.name)
        val structName =
                if (structFromIdent.get() is ParamAST) {
                    ((structFromIdent.get() as ParamAST).type as StructTypeAST).ident.name
                } else {
                    val structIdentDeclareInST = structFromIdent.get() as DeclareStatAST
                    (structIdentDeclareInST.type as StructTypeAST).ident.name
                }
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
        return visitor.visitStructAccessAST(this)
    }

    /** Looks up the identifier in the symbol table to get the struct name
     *  Then looks up the struct name and gets list of fields
     *  Looks through list of fields to find correct index */
    override fun getRealType(table: SymbolTable): TypeAST {
        var typeOfField: TypeAST = AnyTypeAST()
        for (i in structDeclare.fields.indices) {
            if (structDeclare.fields[i].ident.equals(fieldIdent)) {
                typeOfField = structDeclare.fields[i].type
                break
            }
        }
        return typeOfField
    }

}