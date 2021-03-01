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
        val instr = mutableListOf<Instruction>()
        val reg = getNextFreeCalleeReg()
        val loadInstrOfChoice =
                if (choice == PairChoice.FST) {
                    LoadInstr(reg, null, RegisterAddr(reg), Condition.AL)
                } else {
                    LoadInstr(reg, null, RegisterAddrWithOffset(reg, 4, false), Condition.AL)
                }
        instr.addAll(expr.translate())
        instr.add(LoadInstr(reg, null, RegisterAddrWithOffset(Register.SP, SymbolTable.getBytesOfType(type), false), Condition.AL))
        instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg)))
        instr.add(BranchInstr(Condition.AL, RuntimeError.nullReferenceLabel, true))
        CodeGenerator.runtimeErrors.addNullReferenceCheck()
        instr.add(loadInstrOfChoice)
        instr.add(LoadInstr(reg, null, RegisterAddr(reg), Condition.AL))
        return instr
    }
}

enum class PairChoice {
    FST,
    SND
}
