package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.LoadInstr
import wacc.backend.instruction.instrs.MoveInstr
import wacc.backend.instruction.utils.ImmediateInt
import wacc.backend.instruction.utils.ImmediateLabel
import wacc.backend.instruction.utils.ImmediateOperandBool
import wacc.backend.instruction.utils.ImmediateOperandChar
import wacc.frontend.SymbolTable
import wacc.frontend.SymbolTable.Companion.getBytesOfType
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.*

interface LiterAST : ExprAST

class IntLiterAST(val value: Int) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.INT)
    }

    override fun translate(): List<Instruction> {
        return listOf(LoadInstr(getNextFreeCalleeReg(), null, ImmediateInt(value), Condition.AL))
    }
}

class BoolLiterAST(val value: Boolean) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }

    override fun translate(): List<Instruction> {
        return listOf(MoveInstr(Condition.AL, getNextFreeCalleeReg(), ImmediateOperandBool(value)))
    }
}

class StrLiterAST(val value: String) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }

    override fun translate(): List<Instruction> {
        val strLabel = CodeGenerator.dataDirective.addStringLabel(value)
        return listOf(LoadInstr(getNextFreeCalleeReg(), null, ImmediateLabel(strLabel), Condition.AL))
    }
}

class CharLiterAST(val value: Char) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }

    override fun translate(): List<Instruction> {
        return listOf(MoveInstr(Condition.AL, getNextFreeCalleeReg(), ImmediateOperandChar(value)))
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
    lateinit var arrayType: TypeAST
    override fun getRealType(table: SymbolTable): TypeAST {
        if (values.isEmpty()) {
            arrayType = ArrayTypeAST(AnyTypeAST(), 1)
            return arrayType
        }
        val exprType = values[0].getRealType(table)
        if (exprType is ArrayTypeAST) {
            arrayType = ArrayTypeAST(exprType.type, exprType.dimension + 1)
            return arrayType
        }
        arrayType = ArrayTypeAST(exprType, 1)
        return arrayType
    }

    override fun translate(): List<Instruction> {
        TODO()
    }
}
