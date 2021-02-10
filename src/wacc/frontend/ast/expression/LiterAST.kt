package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.ast.type.TypeAST

interface LiterAST : ExprAST

class IntLiterAST(val value: Int) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.INT)
    }
}

class BoolLiterAST(val value: Boolean) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }
}

class StrLiterAST(val value: String) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }
}

class CharLiterAST(val value: Char) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }
}

class NullPairLiterAST : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.NULL)
    }
}

class ArrayLiterAST(val values: List<ExprAST>) : RhsAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        if (values.isEmpty()) {
            return ArrayTypeAST(BaseTypeAST(BaseType.ANY), 1)
        }
        val exprType = values[0].getRealType(table)
        if (exprType is ArrayTypeAST) {
            return ArrayTypeAST(exprType.type, exprType.dimension + 1)
        }
        return ArrayTypeAST(exprType, 1)
    }
}
