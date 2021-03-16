package wacc.frontend.ast.pointer

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.type.PointerTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

class PointerElemAST(val ident: IdentAST) : ExprAST, LhsAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        if (!ident.check(table)) {
            return false
        }
        val identType = ident.getRealType(table)

        return if (identType is PointerTypeAST) {
            true
        } else {
            semanticError("Unable to dereference non-pointer type $identType", ctx)
            false
        }
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val identType = ident.getRealType(table)
        return (identType as PointerTypeAST).type
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitPointerElemAST(this)
    }

}
