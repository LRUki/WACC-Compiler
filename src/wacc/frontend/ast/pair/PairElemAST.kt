package wacc.frontend.ast.pair

import wacc.frontend.SymbolTable
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST

class PairElemAST(val choice: PairChoice, val expr: ExprAST): LhsAST, RhsAST {
    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return expr.getRealType(table)
    }
}

enum class PairChoice {
    FST,
    SND
}