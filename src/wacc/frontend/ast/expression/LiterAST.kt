package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.Instruction
import wacc.backend.translate.enums.Condition
import wacc.backend.translate.enums.MemoryType
import wacc.backend.translate.enums.Register
import wacc.backend.translate.instrs.*
import wacc.backend.translate.utils.*
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
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateInt(value), reg)
        return instrs
    }
}

class BoolLiterAST(val value: Boolean) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        var instrs = emptyList<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateOperandBool(value))
        return instrs
    }
}

class StrLiterAST(val value: String) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        var instrs = emptyList<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        val strLabel = CodeGenerator.dataDirective.addStringLabel(value)
        instrs += LoadInstr(Condition.AL, null, ImmediateLabel(strLabel), reg)
        return instrs
    }
}

class CharLiterAST(val value: Char) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        var instrs = emptyList<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateOperandChar(value))
        return instrs
    }

}

class NullPairLiterAST : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return AnyPairTypeAST()
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        var instrs = emptyList<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateInt(0), reg)
        return instrs
    }
}

class ArrayLiterAST(val values: List<ExprAST>) : RhsAST {
    lateinit var arrayType: TypeAST
    lateinit var symTable: SymbolTable
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

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        val elemSize = getBytesOfType((arrayType as ArrayTypeAST).type)
//        println("This is the bytes of ${arrayType} ${elemSize}")
        //loading the length of array * elemSize + size of INT
        val sizeOfInt = getBytesOfType(BaseTypeAST(BaseType.INT))
        instr.add(LoadInstr(Condition.AL, null,
                ImmediateInt(elemSize * values.size + sizeOfInt), Register.R0))

        instr.add(BranchInstr(Condition.AL, Label("malloc"), true))
        val stackReg = getNextFreeCalleeReg()
        instr.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //add element to stack
        var memType: MemoryType? = null;
        for ((i, expr) in values.withIndex()) {
            if (expr is IdentAST) {
                expr.symTable = symTable
            }
            instr.addAll(expr.translate())

            if ((expr is CharLiterAST) || (expr is BoolLiterAST)) {
                memType = MemoryType.B
            }
            instr.add(StoreInstr(Condition.AL, memType,
                    RegisterAddrWithOffset(stackReg,
                            sizeOfInt + (i * elemSize),
                            false), seeLastUsedCalleeReg()))
            freeCalleeReg()
        }

        //add the length of the array to stack
        instr.add(LoadInstr(Condition.AL, null, ImmediateInt(values.size), getNextFreeCalleeReg()))
        instr.add(StoreInstr(Condition.AL, null,
                RegisterAddr(stackReg), seeLastUsedCalleeReg()))
        freeCalleeReg()
        return instr
    }
}
