package wacc.frontend.ast.pair

import wacc.frontend.SymbolTable
import wacc.frontend.ast.PairTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.exception.SemanticException

class PairElemAST(val choice: PairChoice, val expr: ExprAST): LhsAST, RhsAST {
    override fun check(table: SymbolTable): Boolean {
        expr.check(table)
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val pairType = expr.getRealType(table)
        pairType as PairTypeAST
        return when(choice) {
            PairChoice.FST -> pairType.type1
            PairChoice.SND -> pairType.type2
        }
    }
}

enum class PairChoice {
    FST,
    SND
}