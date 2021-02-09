package wacc.frontend.ast.array

import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.ArrayTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

class ArrayElemAST(val ident: IdentAST, val indices: List<ExprAST>) : ExprAST, LhsAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        ident.check(table)
        val arrayType = ident.getRealType(table)
        if (arrayType !is ArrayTypeAST) {
            semanticError("Expected type Array actual type $arrayType", ctx)
        }
        arrayType as ArrayTypeAST
        if (indices.size != arrayType.dimension) {
            semanticError("Invalid indexing of array, expected dimension " +
                    "${arrayType.dimension} actual type ${indices.size}", ctx)
        }
        indices.forEach { it.check(table) }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return (ident.getRealType(table) as ArrayTypeAST).type
    }
}
