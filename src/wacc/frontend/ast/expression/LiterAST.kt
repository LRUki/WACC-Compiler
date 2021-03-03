package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instrpart.Condition
import wacc.backend.translate.instruction.instrpart.MemoryType
import wacc.backend.translate.instruction.instrpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instrpart.*
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
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateIntMode(value), reg)
        return instrs
    }
}

class BoolLiterAST(val value: Boolean) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.BOOL)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateBoolOperand(value))
        return instrs
    }
}

class StrLiterAST(val value: String) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.STRING)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        val strLabel = CodeGenerator.dataDirective.addStringLabel(value)
        instrs += LoadInstr(Condition.AL, null, ImmediateLabelMode(strLabel), reg)
        return instrs
    }
}

class CharLiterAST(val value: Char) : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return BaseTypeAST(BaseType.CHAR)
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += MoveInstr(Condition.AL, reg, ImmediateCharOperand(value))
        return instrs
    }

}

class NullPairLiterAST : LiterAST {
    override fun getRealType(table: SymbolTable): TypeAST {
        return AnyPairTypeAST()
    }

    override fun translate(): List<Instruction> {
        var reg = getNextFreeCalleeReg()
        val instrs = mutableListOf<Instruction>()
        if (reg == Register.NONE) {  // Use accumulator mode if registers are used up
            reg = Register.R10
            instrs += PushInstr(reg)
        }
        instrs += LoadInstr(Condition.AL, null, ImmediateIntMode(0), reg)
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
        val instrs = mutableListOf<Instruction>()
        val elemSize = getBytesOfType((arrayType as ArrayTypeAST).type)
//        println("This is the bytes of ${arrayType} ${elemSize}")
        //loading the length of array * elemSize + size of INT
        val sizeOfInt = getBytesOfType(BaseTypeAST(BaseType.INT))
        instrs.add(LoadInstr(Condition.AL, null,
                ImmediateIntMode(elemSize * values.size + sizeOfInt), Register.R0))

        instrs.add(BranchInstr(Condition.AL, Label("malloc"), true))
        val stackReg = getNextFreeCalleeReg()
        instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //add element to stack
        var memType: MemoryType? = null
        for ((i, expr) in values.withIndex()) {
            if (expr is IdentAST) {
                expr.symTable = symTable
            }
            instrs.addAll(expr.translate())

            if ((expr is CharLiterAST) || (expr is BoolLiterAST)) {
                memType = MemoryType.B
            }
            instrs.add(StoreInstr(Condition.AL, memType,
                    RegisterAddrWithOffsetMode(stackReg,
                            sizeOfInt + (i * elemSize),
                            false), seeLastUsedCalleeReg()))
            freeCalleeReg()
        }

        //add the length of the array to stack
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(values.size), getNextFreeCalleeReg()))
        instrs.add(StoreInstr(Condition.AL, null,
                RegisterMode(stackReg), seeLastUsedCalleeReg()))
        freeCalleeReg()
        return instrs
    }
}
