package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.MemoryType
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.MoveInstr
import wacc.backend.instruction.instrs.StoreInstr
import wacc.backend.instruction.utils.RegisterAddr
import wacc.backend.instruction.utils.RegisterAddrWithOffset
import wacc.backend.instruction.utils.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.StrLiterAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
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
        val instruction = mutableListOf<Instruction>()
        instruction.addAll(rhs.translate())
        if (rhs is StrLiterAST) {
            stringLabel = CodeGenerator.dataDirective.getStringLabel(rhs.value)
        }
        when (rhs) {
            // only other RHS which requires "setting up"
            is CallRhsAST -> {
                instruction.add(MoveInstr(Condition.AL, Register.R4, RegisterOperand(Register.R0)))
                instruction.add(StoreInstr(Register.R4, null, RegisterAddr(Register.SP), Condition.AL))
            }
        }
        val size = SymbolTable.getBytesOfType(rhs.getRealType(symTable))
        symTable.offsetSize -= size
        val type = rhs.getRealType(symTable)
        var memtype: MemoryType? = null
        if (type is BaseTypeAST) {
            if (type.type == BaseType.BOOL || type.type == BaseType.CHAR) {
                memtype = MemoryType.B
            }
        }
        when (lhs) {
            is IdentAST -> {
                instruction.add(StoreInstr(seeLastUsedCalleeReg(), memtype, RegisterAddrWithOffset(Register.SP, symTable.findOffsetInStack(lhs.name), false), Condition.AL))
            }
            is ArrayElemAST -> {
                TODO("Not yet implemented")
            }
            is PairElemAST -> {
                TODO("Not yet implemented")
            }
        }
        freeCalleeReg()

        return instruction
    }
}
