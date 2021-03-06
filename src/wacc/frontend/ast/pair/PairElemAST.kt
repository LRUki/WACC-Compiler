package wacc.frontend.ast.pair


import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.NullPairLiterAST
import wacc.frontend.ast.type.PairTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a Pair Element
 *
 * @param choice elem command, either 'fst' or 'snd'
 * @param expr evaluating to a pair object
 */
class PairElemAST(val choice: PairChoice, val expr: ExprAST) : LhsAST, RhsAST, AbstractAST() {
    lateinit var type: TypeAST

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (expr is NullPairLiterAST) {
            semanticError("Attempt to access element of a null pair", ctx)
            return false
        }
        if (!expr.check(table)) {
            return false
        }
        if (expr !is IdentAST || expr.getRealType(table) !is PairTypeAST) {
            semanticError("Trying to access the fields of a non pair type", ctx)
            return false
        }
        table.setAccessedField(expr.name)
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val pairType = expr.getRealType(table)
        if (pairType !is PairTypeAST) {
            semanticError("Expected type PAIR, Actual type $pairType", ctx)
        }
        pairType as PairTypeAST
        type = when (choice) {
            PairChoice.FST -> pairType.type1
            PairChoice.SND -> pairType.type2
        }
        return type
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitPairElemAST(this)
    }

}

enum class PairChoice {
    FST,
    SND
}
