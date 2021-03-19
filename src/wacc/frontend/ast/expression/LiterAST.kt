package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.*

interface LiterAST : ExprAST

class IntLiterAST(val value: Int) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.INT)
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitIntLiterAST(this)
    }
}

class BoolLiterAST(val value: Boolean) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitBoolLiterAST(this)
    }
}

class StrLiterAST(val value: String) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitStrLiterAST(this)
    }
}

class CharLiterAST(val value: Char) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitCharLiterAST(this)
    }

}

class NullPairLiterAST : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return AnyPairTypeAST()
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitNullPairLiterAST(this)
    }

}

class ArrayLiterAST(val values: List<ExprAST>) : RhsAST {
    lateinit var arrayType: TypeAST
    lateinit var symTable: SymbolTable
    override fun check(table: SymbolTable): Boolean {
        for (value in values) {
            if (!value.check(table)) {
                return false
            }
            if (value.getRealType(table) is PointerTypeAST && value is OpExpr) {
                (value as OpExpr).setMemoryReferencesAccessed()
            }

        }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        symTable = table
        arrayType = if (values.isEmpty()) {
            ArrayTypeAST(AnyTypeAST(), 1)
        } else {
            val exprType = values[0].getRealType(table)
            if (exprType is ArrayTypeAST) {
                ArrayTypeAST(exprType.type, exprType.dimension + 1)
            } else {
                ArrayTypeAST(exprType, 1)
            }
        }
        return arrayType
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitArrayLiterAST(this)
    }

}
