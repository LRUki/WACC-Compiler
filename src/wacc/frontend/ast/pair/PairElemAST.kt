package wacc.frontend.ast.pair


import wacc.backend.CodeGenerator.runtimeErrors
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.LoadInstr
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.NullPairLiterAST
import wacc.frontend.ast.type.PairTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a Pair Element
 *
 * @param Pair elem command, either 'fst' or 'snd'
 * @param Expression evaluating to a pair object
 */
class PairElemAST(val choice: PairChoice, val expr: ExprAST) : LhsAST, RhsAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (expr is NullPairLiterAST) {
            semanticError("Attempt to access element of a null pair", ctx)
            return false
        }
        return expr.check(table)
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val pairType = expr.getRealType(table)
        if (pairType !is PairTypeAST) {
            semanticError("Expected type PAIR, Actual type $pairType", ctx)
        }
        pairType as PairTypeAST
        return when (choice) {
            PairChoice.FST -> pairType.type1
            PairChoice.SND -> pairType.type2
        }
    }

    override fun translate(): List<Instruction> {
//        TODO("Not yet implemented")
        val instructions = listOf<Instruction>(
//                LoadInstr
        //move
        //branch null
//        load
//        load
//        store
        )



    //movinstr(Register.R0))
//        runtimeErrors.addNullReferenceCheck()
//        BranchInstr(Condition.AL, , true)

//        LDR r4, [sp, #4]
//        30		MOV r0, r4
//        31		BL p_check_null_pointer
//        32		LDR r4, [r4]
//        33		LDR r4, [r4]
//        34		STR r4, [sp]
        return emptyList()
    }
}

enum class PairChoice {
    FST,
    SND
}