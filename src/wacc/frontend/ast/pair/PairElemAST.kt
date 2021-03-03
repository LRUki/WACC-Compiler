package wacc.frontend.ast.pair


import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.RuntimeError
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instrpart.Condition
import wacc.backend.translate.instruction.instrpart.Register
import wacc.backend.translate.instruction.BranchInstr
import wacc.backend.translate.instruction.LoadInstr
import wacc.backend.translate.instruction.MoveInstr
import wacc.backend.translate.instruction.instrpart.*
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
 * @param choice elem command, either 'fst' or 'snd'
 * @param expr evaluating to a pair object
 */
class PairElemAST(val choice: PairChoice, val expr: ExprAST) : LhsAST, RhsAST, AbstractAST() {
    lateinit var type: TypeAST

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
        type = when (choice) {
            PairChoice.FST -> pairType.type1
            PairChoice.SND -> pairType.type2
        }
        return type
    }

    override fun translate(): List<Instruction> {
        val instrs = mutableListOf<Instruction>()

        instrs.addAll(expr.translate())
        val reg = seeLastUsedCalleeReg()
//        instrs.add(LoadInstr(Condition.AL, null, RegisterAddrWithOffset(Register.SP, SymbolTable.getBytesOfType(type), false), reg))
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
        instrs.add(BranchInstr(Condition.AL, RuntimeError.nullReferenceLabel, true))
        CodeGenerator.runtimeErrors.addNullReferenceCheck()
        if (choice == PairChoice.FST) {
            instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg), reg))
        } else {
            instrs.add(LoadInstr(Condition.AL, null, RegisterAddrWithOffsetMode(reg, 4, false), reg))
        }
        return instrs
    }
}

enum class PairChoice {
    FST,
    SND
}
