package wacc.frontend.ast.expression

import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.*
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST

class IdentAST(val name: String) : ExprAST, LhsAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        val stEntry = table.lookupAll(name)
        if (stEntry.isEmpty) {
            semanticError("Variable has not been declared", ctx)
        }
        return true
    }

    override fun toString(): String {
        return name
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val typeOpt = table.lookupAll(name)
        if (typeOpt.isEmpty) { //should never happen because check is called before getRealType
            semanticError("Variable has not been declared", ctx)
        }
        return when (val type = typeOpt.get()) {
            is FuncAST -> type.type
            is DeclareStatAST -> type.type
            is ParamAST -> type.type
            is ArrayTypeAST -> type
            is PairTypeAST -> type
            else -> throw RuntimeException("Unknown class implementing Identifiable")
        }


    }
}
