package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.AddInstr
import wacc.backend.translate.instruction.BranchInstr
import wacc.backend.translate.instruction.Label
import wacc.backend.translate.instruction.MoveInstr
import wacc.backend.translate.CLibrary
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
import wacc.backend.translate.instruction.instructionpart.RegisterOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
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
        val instrs = mutableListOf<Instruction>()

        when (expr) {
            is IdentAST -> {
                val (correctSTScope, offset) = symTable.getSTWithIdentifier(expr.name, (exprType as BaseTypeAST))
                instrs.add(AddInstr(Condition.AL, Register.R4, Register.SP, ImmediateIntOperand(correctSTScope.findOffsetInStack(expr.name) + offset)))
            }
            is ArrayElemAST -> {

            }
            is PairElemAST -> {
                instrs.addAll(expr.translate())
            }
        }
        instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R4)))

        when ((exprType as BaseTypeAST).type) {
            BaseType.INT -> {
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_INT.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.READ_INT)
            }
            BaseType.CHAR -> {
                instrs.add(BranchInstr(Condition.AL, Label(CLibrary.Call.READ_CHAR.toString()), true))
                CodeGenerator.CLib.addCode(CLibrary.Call.READ_CHAR)
            }
            else -> throw RuntimeException("Read can only be used for int or char, semantic check failed")
        }
        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitReadStatAST(this)
    }

}