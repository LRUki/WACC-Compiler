package wacc.frontend.ast.array

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.exception.semanticError

class ArrayElemAST(val ident: IdentAST, val indices: List<ExprAST>) : ExprAST, LhsAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        ident.check(table)
        val arrayType = ident.getRealType(table)
        if (arrayType !is ArrayTypeAST) {
            semanticError("Expected type ${arrayType} ARRAY, Actual type $arrayType", ctx)
        }
        arrayType as ArrayTypeAST
        if (indices.size != arrayType.dimension) {
            semanticError("Invalid assignment of $arrayType ARRAY," +
                    "Expected dimension ${arrayType.dimension}, Actual dimension ${indices.size}", ctx)
        }
        indices.forEach { it.check(table) }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return (ident.getRealType(table) as ArrayTypeAST).type
    }
}
