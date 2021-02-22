package wacc.frontend.ast.expression

import wacc.backend.instruction.Instruction
import wacc.frontend.SymbolTable
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.*

interface LiterAST : ExprAST

class IntLiterAST(val value: Int) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.INT)
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}

class BoolLiterAST(val value: Boolean) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}

class StrLiterAST(val value: String) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}

class CharLiterAST(val value: Char) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}

class NullPairLiterAST : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return AnyPairTypeAST()
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}

class ArrayLiterAST(val values: List<ExprAST>) : RhsAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        if (values.isEmpty()) {
            return ArrayTypeAST(AnyTypeAST(), 1)
        }
        val exprType = values[0].getRealType(table)
        if (exprType is ArrayTypeAST) {
            return ArrayTypeAST(exprType.type, exprType.dimension + 1)
        }
        return ArrayTypeAST(exprType, 1)
    }

    override fun translate(): List<Instruction> {
        TODO("Not yet implemented")
    }
}
