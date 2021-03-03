package wacc.frontend.ast.assign

import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.CLibrary
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
import wacc.frontend.SymbolTable
import wacc.frontend.SymbolTable.Companion.getBytesOfType
import wacc.frontend.ast.AST
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
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
        val instrs = mutableListOf<Instruction>()
        var memtype: MemoryType? = null
        //Malloc space for pair
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(2 * 4), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        val stackReg = getNextFreeCalleeReg()
        instrs.add(MoveInstr(Condition.AL, stackReg, RegisterOperand(Register.R0)))

        //Malloc first element
        instrs.addAll(fst.translate())
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(getBytesOfType(firstType)), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (firstType.isBoolOrChar()) {
            memtype = MemoryType.B
        }
        instrs.add(StoreInstr(memtype, RegisterMode(Register.R0), seeLastUsedCalleeReg()))
        freeCalleeReg()
        instrs.add(StoreInstr(null, RegisterMode(stackReg), Register.R0))

        //Malloc second element
        instrs.addAll(snd.translate())
        instrs.add(LoadInstr(Condition.AL, null, ImmediateIntMode(getBytesOfType(secondType)), Register.R0))
        instrs.add(BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true))
        if (secondType.isBoolOrChar()) {
            memtype = MemoryType.B
        }
        instrs.add(StoreInstr(memtype, RegisterMode(Register.R0), seeLastUsedCalleeReg()))
        freeCalleeReg()
        instrs.add(StoreInstr(null, RegisterAddrWithOffsetMode(stackReg, 4, false), Register.R0))

        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitNewPairRhsAST(this)
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
        argTypes = mutableListOf()
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
        val instrs = mutableListOf<Instruction>()
        var totalBytes = 0
        val argTypesReversed = argTypes.reversed()
        for ((index, arg) in argList.reversed().withIndex()) {
            var memType: MemoryType? = null
            instrs.addAll(arg.translate())
            val bytes = getBytesOfType(argTypesReversed[index])
            symTable.callOffset = bytes
            totalBytes += bytes
            if (argTypesReversed[index].isBoolOrChar()) {
                memType = MemoryType.B
            }
            instrs.add(StoreInstr(memType,
                    RegisterAddrWithOffsetMode(Register.SP, -1 * bytes, true),
                    seeLastUsedCalleeReg()))
            freeCalleeReg()
            if (index == 0) {
                symTable.increaseOffsetForCall = 4
            }
        }
        symTable.callOffset = 0
        symTable.increaseOffsetForCall = 0

        val funcLabel = FunctionLabel(ident.name)
        instrs.add(BranchInstr(Condition.AL, funcLabel, true))
        instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(totalBytes), false))
        instrs.add(MoveInstr(Condition.AL, getNextFreeCalleeReg(), RegisterOperand(Register.R0)))
        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitCallRhsAST(this)
    }

}
