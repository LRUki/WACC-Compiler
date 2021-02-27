package wacc.frontend.ast.assign

import wacc.backend.CodeGenerator
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.*
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.Translatable
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.type.PairTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.ast.type.Typed
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
    override fun check(table: SymbolTable): Boolean {
        return (fst.check(table) && snd.check(table))
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return PairTypeAST(fst.getRealType(table), snd.getRealType(table))
    }

    override fun translate(): List<Instruction> {
//        TODO("Not yet implemented")

// BL malloc
// MOV r4, r0
// LDR r5, =69
// LDR r0, =4

        val fstTranslation = fst.translate()
        val mallocAndStoreFst = listOf(
                BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true),
                MoveInstr(Condition.AL, Register.R4, RegisterOperand(Register.R0)),
                LoadInstr(CodeGenerator.getNextFreeCalleeReg(), null,
                        RegisterAddr(CodeGenerator.getLastUsedCalleeReg()), Condition.AL),
                LoadInstr(Register.R0, null, ImmediateInt(4), Condition.AL)
        )

// BL malloc
// STR r5, [r0]
// STR r0, [r4]
// LDR r5, =420
// LDR r0, =4

        val sndTranslation = snd.translate()
        val mallocAndStoreSnd = listOf(
                BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true),
                StoreInstr(CodeGenerator.getNextFreeCalleeReg(), null, RegisterAddr(Register.R0), Condition.AL),
                StoreInstr(Register.R0, null, RegisterAddr(Register.R4), Condition.AL),
                LoadInstr(CodeGenerator.getNextFreeCalleeReg(), null,
                        RegisterAddr(CodeGenerator.getLastUsedCalleeReg()), Condition.AL),
                LoadInstr(Register.R0, null, ImmediateInt(4), Condition.AL)
        )

// BL malloc
// STR r5, [r0]
// STR r0, [r4, #4]
// STR r4, [sp]
// ADD sp, sp, #4
// LDR r0, =0

        val mallocAndStorePair = listOf(
                BranchInstr(Condition.AL, Label(CLibrary.LibraryFunctions.MALLOC.toString()), true),
                StoreInstr(CodeGenerator.getNextFreeCalleeReg(), null, RegisterAddr(Register.R0), Condition.AL),
                StoreInstr(Register.R0, null, RegisterAddrWithOffset(Register.R4, 4, false), Condition.AL),
                StoreInstr(Register.R4, null, RegisterAddr(Register.SP), Condition.AL),
                AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(4), false),
                LoadInstr(Register.R0, null, ImmediateInt(0), Condition.AL)
        )

        return emptyList()
    }

}

/**
 * AST node to represent a Function Call
 *
 * @property ident Identifier representing function name
 * @property argList List of expression as arguments for the function
 */
class CallRhsAST(val ident: IdentAST, val argList: List<ExprAST>) : RhsAST, AbstractAST() {
    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!ident.check(table)) {return false}
        val funcAst = table.lookupAll(ident.name).get()

        if (funcAst !is FuncAST) {
            semanticError("No function called $ident", ctx)
            return false
        }
        //funcAst has implicitly been cast to a FuncAST
        /* Check all the arguments and for the correct number of them */
        argList.forEach { if (!it.check((table))) {return false} }
        if (funcAst.paramList.size != argList.size) {
            semanticError("Incorrect number of arguments, Expected ${funcAst.paramList.size}" +
                    "arguments, Actually got ${argList.size}", ctx)
            return false
        }
        for (i in argList.indices) {
            val argType = argList[i].getRealType(table)
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
        val funcLabel = FunctionLabel(ident.name)
        return listOf(BranchInstr(Condition.AL, funcLabel, true))
    }

}
