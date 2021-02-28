package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.MemoryType
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.*
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.StrLiterAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Assignment Statement (LHS = RHS)
 *
 * @property lhs LhsAST is the node to be assigned to
 * @property rhs RhsAST is the value we are assigning
 */
class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST, AbstractAST() {
    lateinit var stringLabel: String

    private fun lhsIsAFunction(table: SymbolTable): Boolean {
        if (lhs is IdentAST) {
            val fName = table.lookupAll(lhs.name)
            if (fName.isPresent && fName.get() is FuncAST) {
                return true
            }
        }
        return false
    }

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!lhs.check(table) || !rhs.check(table)) {
            return false
        }
        var leftType = lhs.getRealType(table)
        val rightType = rhs.getRealType(table)
        if (leftType is ArrayTypeAST) {
            leftType = leftType.type
        }
        if (lhsIsAFunction(table)) {
            semanticError("Cannot assign a value to a function", ctx)
            return false
        }

        if (leftType != rightType) {
            semanticError("Type mismatch, $rightType cannot be assigned to $leftType", ctx)
            return false
        }
        return true
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        instr.addAll(rhs.translate())
        val calleeReg = seeLastUsedCalleeReg()
        if (rhs is StrLiterAST) {
            stringLabel = CodeGenerator.dataDirective.getStringLabel(rhs.value)
        }
        when (rhs) {
            // only other RHS which requires "setting up"
            is CallRhsAST -> {
                instr.add(MoveInstr(Condition.AL, Register.R4, RegisterOperand(Register.R0)))
                instr.add(StoreInstr(Condition.AL, null, RegisterAddr(Register.SP), calleeReg))
            }
        }
        val rhsType = rhs.getRealType(symTable)
        val rhsBytes = SymbolTable.getBytesOfType(rhsType)
        symTable.decreaseOffset(lhs, rhsType)
        var memtype: MemoryType? = null
        if (rhsType is BaseTypeAST) {
            if (rhsType.type == BaseType.BOOL || rhsType.type == BaseType.CHAR) {
                memtype = MemoryType.B
            }
        }
        when (lhs) {
            is IdentAST -> {
                val (correctSTScope, offset) = symTable.getSTWithIdentifier(lhs.name, rhsType)
                instr.add(StoreInstr(Condition.AL, memtype, RegisterAddrWithOffset(Register.SP, correctSTScope.findOffsetInStack(lhs.name) + offset , false), calleeReg))
            }
            is ArrayElemAST -> {
                val stackReg = getNextFreeCalleeReg()
                instr.add(AddInstr(Condition.AL, stackReg , Register.SP, ImmediateOperandInt(0), false))
                instr.add(LoadInstr(Condition.AL, null, RegisterAddr(stackReg), stackReg))
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
                instr.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(stackReg)))
                instr.add(BranchInstr(Condition.AL, RuntimeError.checkArrayBoundsLabel, true))
                CodeGenerator.runtimeErrors.addArrayBoundsCheck()
                instr.add(AddInstr(Condition.AL, stackReg, stackReg, ImmediateOperandInt(rhsBytes)))
                instr.add(AddInstr(Condition.AL, stackReg, stackReg, RegisterOperand(seeLastUsedCalleeReg()))) // ADD LSL #2
                instr.add(StoreInstr(Condition.AL, null, RegisterAddr(stackReg), calleeReg))
            }
            is PairElemAST -> {
                TODO("Not yet implemented")
            }
        }
        freeCalleeReg()

        return instr
    }
}
