package wacc.frontend.ast.statement.block

import wacc.backend.CodeGenerator
import wacc.backend.instruction.*
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.AddInstr
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.CompareInstr
import wacc.backend.instruction.instrs.SubInstr
import wacc.backend.instruction.utils.ImmediateOperandInt
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError

/**
 * AST node to represent a WHILE DO DONE statement
 *
 * @property cond Boolean expression for the condition
 * @property body List of statements to be executed when cond == true
 */
class WhileStatAST(val cond: ExprAST, val body: List<StatAST>) : StatAST, AbstractAST() {
    lateinit var blockST: SymbolTable

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!cond.check(table)) {return false}
        val condType = cond.getRealType(table)
        if (condType != TypeInstance.boolTypeInstance) {
            semanticError("While condition must evaluate to a BOOL, but was actually $condType", ctx)
            return false
        }
        blockST = SymbolTable(table)
        body.forEach { if (!it.check(blockST)) {return false} }
        return true
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        val condLabel = CodeGenerator.getNextLabel()
        val bodyLabel = CodeGenerator.getNextLabel()
        instr.add(BranchInstr(Condition.AL, condLabel,  false))

        instr.add(bodyLabel)
        val stackOffset = blockST.getStackOffset()
        if (stackOffset > 0) {
            instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        body.forEach { instr.addAll(it.translate()) }

        instr.addAll(cond.translate())
        instr.add(CompareInstr(Register.R4, null, 1))
        instr.add(BranchInstr(Condition.EQ, bodyLabel, false))
        if (stackOffset > 0) {
            instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        return instr
    }
}