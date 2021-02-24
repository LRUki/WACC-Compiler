package wacc.frontend.ast.statement.block

import wacc.backend.CodeGenerator
import wacc.backend.instruction.*
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.BranchInstr
import wacc.backend.instruction.instrs.CompareInstr
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

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!cond.check(table)) {return false}
        val condType = cond.getRealType(table)
        if (condType != TypeInstance.boolTypeInstance) {
            semanticError("While condition must evaluate to a BOOL, but was actually $condType", ctx)
            return false
        }
        val blockST = SymbolTable(table)
        body.forEach { if (!it.check(blockST)) {return false} }
        return true
    }

    override fun translate(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        val condLabel = CodeGenerator.getNextLabel()
        val bodyLabel = CodeGenerator.getNextLabel()
        instructions.add(BranchInstr(Condition.AL, condLabel,  false))

        instructions.add(bodyLabel)
        body.forEach { instructions.addAll(it.translate()) }

        instructions.addAll(cond.translate())
        instructions.add(CompareInstr(Condition.AL, Register.R4, null, 1))
        instructions.add(BranchInstr(Condition.EQ, bodyLabel, false))
        return instructions
    }
}