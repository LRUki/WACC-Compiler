package wacc.frontend.ast.pair

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.PairTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.NullPairLiterAST
import wacc.frontend.exception.SemanticException

class PairElemAST(val choice: PairChoice, val expr: ExprAST): LhsAST, RhsAST {
    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        if (expr is NullPairLiterAST) {
            semanticError("Attempt to access element of a null pair")
        }
        expr.check(table)
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val pairType = expr.getRealType(table)
        if (pairType !is PairTypeAST) {
            semanticError("Expected type Pair actual type $pairType")
        }
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