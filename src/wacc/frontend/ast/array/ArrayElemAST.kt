package wacc.frontend.ast.array

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.ArrayTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.assign.LhsAST

class ArrayElemAST(val ident: IdentAST, val indices: List<ExprAST>) : ExprAST, LhsAST {

    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        ident.check(table)
        val arrayType = ident.getRealType(table)
        if (arrayType !is ArrayTypeAST) {
            semanticError("Expected type Array actual type $arrayType")
        }
        arrayType as ArrayTypeAST
        if (indices.size != arrayType.dimension) {
            semanticError("Invalid indexing of array, expected dimension " +
                    "${arrayType.dimension} actual type ${indices.size}")
        }
        indices.forEach { it.check(table) }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return (ident.getRealType(table) as ArrayTypeAST).type
    }
}
