package wacc.frontend.ast.statement.nonblock

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.LoadInstr
import wacc.backend.instruction.instrs.MoveInstr
import wacc.backend.instruction.utils.ImmediateInt
import wacc.backend.instruction.utils.ImmediateOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.ast.type.PairTypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Assignment Statement (LHS = RHS)
 *
 * @property lhs LhsAST is the node to be assigned to
 * @property rhs RhsAST is the value we are assigning
 */
class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST, AbstractAST() {

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
        if (!lhs.check(table) || !rhs.check(table)) {return false}
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
        when (val type = lhs.getRealType(symTable)) {
            is BaseTypeAST -> {
                when (type.type) { //TODO()
                    BaseType.INT -> {
                        instr.add(LoadInstr(Register.R4, null, ImmediateInt(0), Condition.AL))
                    }
                    BaseType.BOOL -> {
                        instr.add(MoveInstr(Condition.AL, Register.R4, ImmediateOperand(0)))
                    }
                    BaseType.CHAR -> {
                        // should be ='CHAR'
                        instr.add((MoveInstr(Condition.AL, Register.R4, ImmediateOperand(0))))
                    }
                    BaseType.STRING -> {
                        // this should have the msg directive as operand
                        instr.add(LoadInstr(Register.R4, null, ImmediateInt(0), Condition.AL))
                    }
                }
            }
            is ArrayTypeAST -> TODO()
            is PairTypeAST ->  TODO()

        }
        return instr
    }
}