package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.LoadInstr
import wacc.backend.translate.instruction.StoreInstr
import wacc.backend.translate.instruction.instructionpart.RegisterMode
import wacc.backend.translate.instruction.instructionpart.RegisterAddrWithOffsetMode
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
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
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(rhs.translate())
        val calleeReg = seeLastUsedCalleeReg()
        if (rhs is StrLiterAST) {
            stringLabel = CodeGenerator.dataDirective.getStringLabel(rhs.value)
        }

        val rhsType = rhs.getRealType(symTable)
        var memtype: MemoryType? = null
        if (rhsType.isBoolOrChar()) {
            memtype = MemoryType.B
        }

        when (rhs) {
            // only other RHS which requires "setting up"
            is CallRhsAST -> {
                var offset = 0
                if (lhs is IdentAST) {
                    offset = symTable.findOffsetInStack(lhs.name)
                }
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, offset, false), calleeReg))
                freeCalleeReg()
                return instrs
            }
            is PairElemAST -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(calleeReg), calleeReg))
            }
        }

        symTable.decreaseOffset(lhs, rhsType)
        when (lhs) {
            is IdentAST -> {
                val (correctSTScope, offset) = symTable.getSTWithIdentifier(lhs.name, rhsType)
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterAddrWithOffsetMode(Register.SP, correctSTScope.findOffsetInStack(lhs.name) + offset, false), calleeReg))
            }
            is ArrayElemAST -> {
                instrs.addAll(lhs.translate())
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterMode(seeLastUsedCalleeReg()), calleeReg))
                freeCalleeReg()
            }
            is PairElemAST -> {
                instrs.addAll(lhs.translate())
                instrs.add(StoreInstr(Condition.AL, memtype, RegisterMode(seeLastUsedCalleeReg()), calleeReg))
                freeCalleeReg()
            }
        }

        freeCalleeReg()

        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitAssignStatAST(this)
    }

}
