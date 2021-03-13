package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.type.*
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Identifier
 *
 * @property name Name of the identifier
 */
class IdentAST(val name: String) : ExprAST, LhsAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        symTable = table
        val stEntry = table.lookupAll(name)
        if (stEntry.isEmpty) {
            semanticError("Variable $name has not been declared", ctx)
            return false
        }
        return true
    }

    override fun toString(): String {
        return name
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val typeOpt = table.lookupAll(name)
        if (typeOpt.isEmpty) { //should never happen because check is called before getRealType
            semanticError("Variable $name has not been declared", ctx)
            throw RuntimeException("Undeclared variable should have been spotted before getting type")
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


    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitIdentAST(this)
    }

    override fun weight(): Int {
        return 1
    }

}
