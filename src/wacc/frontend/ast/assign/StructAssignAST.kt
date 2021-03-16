package wacc.frontend.ast.assign

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.type.*
import wacc.frontend.ast.type.StructTypeAST.Companion.defStructIdent

class StructAssignAST(val assignments: List<RhsAST>) : RhsAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        symTable = table
        assignments.forEach {
            if (!it.check(table)) {
                return false
            }
        }
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitStructAssignAST(this)
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return StructTypeAST(defStructIdent)
    }

}