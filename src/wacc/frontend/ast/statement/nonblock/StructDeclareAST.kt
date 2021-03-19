package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.Identifiable
import wacc.frontend.ast.type.StructFieldAST
import wacc.frontend.exception.semanticError

class StructDeclareAST(val ident: IdentAST, val fields: List<StructFieldAST>) : Identifiable, StatAST, AbstractAST() {
    var totalSizeOfFields: Int = 0
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

        /** Logic for working out the overall struct size*/
        for (field in fields) {
            if (field.type.isBoolOrChar()) {
                totalSizeOfFields += 1
            } else {
                totalSizeOfFields += 4
            }
        }
        table.add(ident.name, this)
        return true
    }

    /** Returns the offset of the provided field in the struct */
    fun getOffsetInStruct(fieldIdent: IdentAST): Int {
        var accessOffset = 0
        for (field in fields) {
            if (fieldIdent.equals(field.ident)) {
                break
            }
            accessOffset += SymbolTable.getBytesOfType(field.type)
        }
        return accessOffset
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitStructDeclareAST(this)
    }
}

