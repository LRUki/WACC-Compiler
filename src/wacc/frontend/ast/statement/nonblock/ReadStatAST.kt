package wacc.frontend.ast.statement.nonblock

import wacc.backend.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node representing Read Statement
 *
 * @property expr LhsAST node to read data into
 */
class ReadStatAST(val expr: LhsAST) : StatAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr.check(table)) {return false}
        val exprType = expr.getRealType(table)
        if (exprType != TypeInstance.charTypeInstance && !exprType.equals(TypeInstance.intTypeInstance)) {
            semanticError("Expected type INT or CHAR, Actual type $exprType", ctx)
            return false
        }
        return true
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}