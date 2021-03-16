package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Assignment Statement (LHS = RHS)
 *
 * @property lhs LhsAST is the node to be assigned to
 * @property rhs RhsAST is the value we are assigning
 */
class CallStatAST(val rhs: CallRhsAST) : StatAST, AbstractAST() {
    lateinit var stringLabel: String

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!rhs.check(table)) {
            return false
        }

        val rightType = rhs.getRealType(table)

        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitCallStatAST(this)
    }


}
