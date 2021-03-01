package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.MemoryType
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.LoadInstr
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
        val rhsType = rhs.getRealType(symTable)
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
                instruction.add(StoreInstr(seeLastUsedCalleeReg(), memtype, RegisterAddrWithOffset(Register.SP, correctSTScope.findOffsetInStack(lhs.name) + offset , false), Condition.AL))
            }
            is ArrayElemAST -> {
                TODO("Not yet implemented")
            }
            is PairElemAST -> {
                instruction.addAll(lhs.expr.translate())
//                instruction.add(StoreInstr(getNextFreeCalleeReg(), null, RegisterAddr(seeLastUsedCalleeReg()), Condition.AL))
//                freeCalleeReg()
//                instruction.add(StoreInstr(Condition.AL, null, RegisterAddr(seeLastUsedCalleeReg()), calleeReg))
//                freeCalleeReg()

//                instruction.add(LoadInstr(seeLastUsedCalleeReg(),null, RegisterAddr(Register.R0), Condition.AL))
//                LDR r4, =42
//                TODO("Not yet implemented")
            }
        }
        //freeCalleeReg()

        return instruction
    }
}
