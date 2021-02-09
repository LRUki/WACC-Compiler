package wacc.frontend.ast.expression

import wacc.frontend.SymbolTable
import wacc.frontend.ast.ArrayTypeAST
import wacc.frontend.ast.BaseType
import wacc.frontend.ast.BaseTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.assign.RhsAST

interface LiterAST: ExprAST

class IntLiterAST(val value: Int): LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.INT)
    }
}

class BoolLiterAST(val value: Boolean): LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }
}

class StrLiterAST(val value: String): LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }
}

class CharLiterAST(val value: Char): LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }
}

class NullPairLiterAST: LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.NULL)
    }
}

//TODO cover case empty array
class ArrayLiterAST(val values: List<ExprAST>): RhsAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        if (values.size  == 0) {
            return ArrayTypeAST(BaseTypeAST(BaseType.ANY), 0)
        }
        val exprType = values[0].getRealType(table)
        if (exprType is ArrayTypeAST) {
            return exprType
        }
        return ArrayTypeAST(exprType, 0)
    }
}
