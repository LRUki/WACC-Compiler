package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node representing Read Statement
 *
 * @property expr LhsAST node to read data into
 */
class ReadStatAST(val expr: LhsAST) : StatAST, AbstractAST() {
    lateinit var exprType: TypeAST

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr.check(table)) {
            return false
        }
        exprType = expr.getRealType(table)
        if (exprType != TypeInstance.charTypeInstance && !exprType.equals(TypeInstance.intTypeInstance)) {
            semanticError("Expected type INT or CHAR, Actual type $exprType", ctx)
            return false
        }
        if (expr is IdentAST) {
            table.setAccessedField(expr.name)
            table.setAssignedField(expr.name)
        }
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitReadStatAST(this)
    }

}