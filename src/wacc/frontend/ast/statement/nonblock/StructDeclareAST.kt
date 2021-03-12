package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.StructFieldAST
import wacc.frontend.exception.semanticError

class StructDeclareAST(val ident: IdentAST, val fields: List<StructFieldAST>) : Identifiable, StatAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        symTable = table
        val identName = table.lookup(ident.name)

        if (identName.isPresent && identName.get() !is FuncAST) {
            semanticError("Type $ident already exists", ctx)
            return false
        }

        fields.forEach {
            if (!it.check(table)) {
                return false
            }
        }
        table.add(ident.name, this)
        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        TODO("Not yet implemented")
    }
}

