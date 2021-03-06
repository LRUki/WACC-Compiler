package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.OpExpr
import wacc.frontend.ast.struct.StructAccessAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.PointerTypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Assignment Statement (LHS = RHS)
 *
 * @property lhs LhsAST is the node to be assigned to
 * @property rhs RhsAST is the value we are assigning
 */
class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST, AbstractAST() {
    lateinit var stringLabel: String

    private fun lhsIsAFunction(table: SymbolTable): Boolean {
        if (lhs is IdentAST) {
            val fName = table.lookupAll(lhs.name)
            if (fName.isPresent && fName.get() is FuncAST) {
                return true
            }
        }
        return false
    }

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!lhs.check(table) || !rhs.check(table)) {
            return false
        }
        var leftType = lhs.getRealType(table)
        val rightType = rhs.getRealType(table)
        if (leftType is ArrayTypeAST) {
            leftType = leftType.type
        }
        if (lhsIsAFunction(table)) {
            semanticError("Cannot assign a value to a function", ctx)
            return false
        }

        if (leftType != rightType) {
            semanticError("Type mismatch, $rightType cannot be assigned to $leftType", ctx)
            return false
        }
        var name = ""
        when (lhs) {
            is IdentAST -> {
                name = lhs.name
            }
            is ArrayElemAST -> {
                name = lhs.ident.name
            }
            is PairElemAST -> {
                name = (lhs.expr as IdentAST).name
            }
            is PointerElemAST -> {
                name = lhs.ident.name
            }
            is StructAccessAST -> {
                name = lhs.structIdent.name
            }
        }
        if (rightType is PointerTypeAST && rhs is OpExpr) {
            (rhs as OpExpr).setMemoryReferencesAccessed()
        }
        if (rhs is CallRhsAST) {
            if (lhs is IdentAST) {
                symTable.setAssignedField(lhs.name)
                symTable.setAccessedField(lhs.name)
            }
        }
        if (lhs is PointerElemAST) {
            if (rhs is IdentAST) {
                symTable.setAccessedField(rhs.name)
            }
        }
        table.setAssignedField(name)
        table.setAccessedField(name)
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitAssignStatAST(this)
    }


}
