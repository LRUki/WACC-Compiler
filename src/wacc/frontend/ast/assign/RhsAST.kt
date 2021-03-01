package wacc.frontend.ast.assign

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
import wacc.frontend.ast.AST
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.Translatable
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.type.*
import wacc.frontend.exception.semanticError

/**
 * Implemented by AST nodes that can be on the right hand-side of an assignment statement.
 * Implements Typed interface to get underlying types during declare and assign statements
 */
interface RhsAST : AST, Typed, Translatable

/**
 * AST node to represent a New Pair
 *
 * @property fst First expression of a pair
 * @property snd Second expression of a pair
 */
class NewPairRhsAST(val fst: ExprAST, val snd: ExprAST) : RhsAST {
    lateinit var firstType: TypeAST
    lateinit var secondType: TypeAST

    override fun check(table: SymbolTable): Boolean {
        return (fst.check(table) && snd.check(table))
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        firstType = fst.getRealType(table)
        secondType = snd.getRealType(table)
        return PairTypeAST(firstType, secondType)
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        var memtype: MemoryType? = null
        //Malloc space for pair
        instr.add(LoadInstr(Condition.AL, null, ImmediateInt(2 * 4), Register.R0))
        instr.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        val stackReg = getNextFreeCalleeReg()
        instr.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //Malloc first element
        instr.addAll(fst.translate())
        instr.add(LoadInstr(Condition.AL, null, ImmediateInt(getBytesOfType(firstType)), Register.R0))
        instr.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (firstType.equals(BaseTypeAST(BaseType.BOOL)) // TODO() Refactor this
                || firstType.equals(BaseTypeAST(BaseType.CHAR))) {
            memtype = MemoryType.B
        }
        instr.add(StoreInstr(Condition.AL, memtype, RegisterAddr(Register.R0), seeLastUsedCalleeReg()))
        freeCalleeReg()
        instr.add(StoreInstr(Condition.AL, null, RegisterAddr(stackReg), Register.R0))

        //Malloc second element
        instr.addAll(snd.translate())
        instr.add(LoadInstr(Condition.AL, null, ImmediateInt(getBytesOfType(secondType)), Register.R0))
        instr.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (secondType.equals(BaseTypeAST(BaseType.BOOL)) // TODO() Refactor this
                || secondType.equals(BaseTypeAST(BaseType.CHAR))) {
            memtype = MemoryType.B
        }
        instr.add(StoreInstr(Condition.AL, memtype, RegisterAddr(Register.R0), seeLastUsedCalleeReg()))
        freeCalleeReg()
        instr.add(StoreInstr(Condition.AL, null, RegisterAddrWithOffset(stackReg, 4, false), Register.R0))

        return instr
    }

}

/**
 * AST node to represent a Function Call
 *
 * @property ident Identifier representing function name
 * @property argList List of expression as arguments for the function
 */
class CallRhsAST(val ident: IdentAST, val argList: List<ExprAST>) : RhsAST, AbstractAST() {
    lateinit var argTypes: MutableList<TypeAST>
    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!ident.check(table)) {
            return false
        }
        val funcAst = table.lookupAll(ident.name).get()

        if (funcAst !is FuncAST) {
            semanticError("No function called $ident", ctx)
            return false
        }
        //funcAst has implicitly been cast to a FuncAST
        /* Check all the arguments and for the correct number of them */
        argList.forEach {
            if (!it.check((table))) {
                return false
            }
        }
        if (funcAst.paramList.size != argList.size) {
            semanticError("Incorrect number of arguments, Expected ${funcAst.paramList.size}" +
                    "arguments, Actually got ${argList.size}", ctx)
            return false
        }
        argTypes = mutableListOf<TypeAST>()
        for (i in argList.indices) {
            val argType = argList[i].getRealType(table)
            argTypes.add(argType)
            val paramType = funcAst.paramList[i].type
            if (argType != paramType) {
                semanticError("Type mismatch, Expected type $paramType, Actual type $argType", ctx)
                return false
            }
        }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return ident.getRealType(table)
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        val totalLength = argTypes.size - 1
        var totalBytes = 0
        var memType: MemoryType? = null
        for ((index, arg) in argList.reversed().withIndex()) {
            instr.addAll(arg.translate())
            val bytes = getBytesOfType(argTypes[(totalLength - index)])
            totalBytes += bytes
            if (argTypes[(totalLength - index)].equals(BaseTypeAST(BaseType.BOOL)) // TODO() Refactor this
                    || argTypes[(totalLength - index)].equals(BaseTypeAST(BaseType.CHAR))) {
                memType = MemoryType.B
            }
            instr.add(StoreInstr(Condition.AL, memType, RegisterAddrWithOffset(Register.SP, -1 * bytes, true), seeLastUsedCalleeReg()))
            freeCalleeReg()
            if (index == 0) {
                symTable.increaseOffsetForCall = true
            }
        }
        symTable.increaseOffsetForCall = false

//        argList.reversed().forEach {
//            instr.addAll(it.translate())
//            val bytes = getBytesOfType(it.getRealType(symTable))
//            instr.add(StoreInstr(seeLastUsedCalleeReg(), null, RegisterAddrWithOffset(Register.SP, -1 * bytes, true), Condition.AL))
//            freeCalleeReg()
//        }

        val funcLabel = FunctionLabel(ident.name)
        instr.add(BranchInstr(Condition.AL, funcLabel, true))
        instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(totalBytes), false))
        instr.add(MoveInstr(Condition.AL, getNextFreeCalleeReg(), RegisterOperand(Register.R0)))
        return instr
    }

}
