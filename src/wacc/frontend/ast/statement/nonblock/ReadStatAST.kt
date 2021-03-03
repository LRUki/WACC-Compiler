package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.translate.Instruction
import wacc.backend.translate.enums.Condition
import wacc.backend.translate.enums.Register
import wacc.backend.translate.instrs.AddInstr
import wacc.backend.translate.instrs.BranchInstr
import wacc.backend.translate.instrs.Label
import wacc.backend.translate.instrs.MoveInstr
import wacc.backend.translate.utils.CLibrary
import wacc.backend.translate.utils.ImmediateOperandInt
import wacc.backend.translate.utils.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node representing Read Statement
 *
 * @property expr LhsAST node to read data into
 */
class ReadStatAST(val expr: LhsAST) : StatAST, AbstractAST() {
    lateinit var exprType: TypeAST

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr.check(table)) {
            return false
        }
        exprType = expr.getRealType(table)
        if (exprType != TypeInstance.charTypeInstance && !exprType.equals(TypeInstance.intTypeInstance)) {
            semanticError("Expected type INT or CHAR, Actual type $exprType", ctx)
            return false
        }
        return true
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()

        when (expr) {
            is IdentAST -> {
                val (correctSTScope, offset) = symTable.getSTWithIdentifier(expr.name, (exprType as BaseTypeAST))
                instr.add(AddInstr(Condition.AL, Register.R4, Register.SP, ImmediateOperandInt(correctSTScope.findOffsetInStack(expr.name) + offset)))
            }
            is ArrayElemAST -> {

            }
            is PairElemAST -> {
                instr.addAll(expr.translate())
            }
        }
        instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R4)))

        when ((exprType as BaseTypeAST).type) {
            BaseType.INT -> {
                instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_INT.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.READ_INT)
            }
            BaseType.CHAR -> {
                instr.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_CHAR.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.READ_CHAR)
            }
            else -> throw RuntimeException("Read can only be used for int or char, semantic check failed")
        }
        return instr
    }
}