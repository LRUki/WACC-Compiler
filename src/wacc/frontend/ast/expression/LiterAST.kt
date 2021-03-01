package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.MemoryType
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.*
import wacc.frontend.SymbolTable
import wacc.frontend.SymbolTable.Companion.getBytesOfType
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.*

interface LiterAST : ExprAST

class IntLiterAST(val value: Int) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.INT)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        var instrs = emptyList<Instruction>()
        if (reg == Register.CPSR) {
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(reg, null, ImmediateInt(value), Condition.AL)
        return instrs
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
        return listOf(LoadInstr(getNextFreeCalleeReg(), null, ImmediateInt(0), Condition.AL))
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
        val instr = mutableListOf<Instruction>()
        val elemSize = getBytesOfType((arrayType as ArrayTypeAST).type)
        println("This is the bytes of ${arrayType} ${elemSize}")
        //loading the length of array * elemSize + size of INT
        val sizeOfInt = getBytesOfType(BaseTypeAST(BaseType.INT))
        instr.add(LoadInstr(Register.R0, null,
                ImmediateInt(elemSize * values.size + sizeOfInt), Condition.AL))

        instr.add(BranchInstr(Condition.AL, Label("malloc"), true))
        val stackReg = getNextFreeCalleeReg()
        instr.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //add element to stack
        var memType: MemoryType? = null;
        for ((i, expr) in values.withIndex()) {
            instr.addAll(expr.translate())

            if ((expr is CharLiterAST) || (expr is BoolLiterAST)) {
                memType = MemoryType.B
            }
            instr.add(StoreInstr(seeLastUsedCalleeReg(), memType,
                    RegisterAddrWithOffset(stackReg,
                            sizeOfInt + (i * elemSize),
                            false), Condition.AL))
            freeCalleeReg()
        }

        //add the length of the array to stack
        instr.add(LoadInstr(getNextFreeCalleeReg(), null, ImmediateInt(values.size), Condition.AL))
        instr.add(StoreInstr(seeLastUsedCalleeReg(), null,
                RegisterAddr(stackReg), Condition.AL))
        freeCalleeReg()
        return instr
    }
}
