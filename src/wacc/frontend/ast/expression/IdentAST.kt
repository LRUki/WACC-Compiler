package wacc.frontend.ast.expression

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.ArrayTypeAST
import wacc.frontend.ast.DeclareStatAST
import wacc.frontend.ast.PairTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST

class IdentAST(val name: String) : ExprAST, LhsAST {

    lateinit var ctx: ParserRuleContext

    override fun getContext(): ParserRuleContext {
        return ctx;
    }

    override fun check(table: SymbolTable): Boolean {
        val stEntry = table.lookupAll(name)
        if (stEntry.isEmpty) {
            semanticError("Variable has not been declared")
        }
        return true
    }

    override fun toString(): String {
        return name
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val typeOpt = table.lookupAll(name)
        if (typeOpt.isEmpty) { //should never happen because check is called before getRealType
            semanticError("Variable has not been declared")
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
