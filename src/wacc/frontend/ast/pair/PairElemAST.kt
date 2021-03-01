package wacc.frontend.ast.pair


import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.LoadInstr
import wacc.backend.instruction.instrs.MoveInstr
import wacc.backend.instruction.instrs.StoreInstr
import wacc.backend.instruction.utils.*
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

        val loadInstrOfChoice =
                if (choice == PairChoice.FST) {
                    LoadInstr(Register.R4, null, RegisterAddr(Register.R4), Condition.AL)
                } else {
                    LoadInstr(Register.R4, null, RegisterAddrWithOffset(Register.R4, 4, false), Condition.AL)
                }
        CodeGenerator.runtimeErrors.addNullReferenceCheck()
        return listOf(
//                LoadInstr(Register.R4, null,
//                        RegisterAddrWithOffset(Register.SP, 4, false), Condition.AL),
                MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R4)),
                BranchInstr(Condition.AL, RuntimeError.nullReferenceLabel, true),
                loadInstrOfChoice,
                LoadInstr(Register.R4, null, RegisterAddr(Register.R4), Condition.AL),
                StoreInstr(Register.R4, null, RegisterAddr(Register.SP), Condition.AL)
        )
    }
}

enum class PairChoice {
        FST,
        SND
}
