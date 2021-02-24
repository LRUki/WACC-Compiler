package wacc.frontend.ast.statement.block

import wacc.backend.CodeGenerator.getNextLabel
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
 * AST node to represent a IF THEN ELSE FI statement
 *
 * @property cond Boolean expression for the condition
 * @property thenBody List of statements to be executed when cond == true
 * @property elseBody List of statements to be executed when cond == false
 */
class IfStatAST(val cond: ExprAST, val thenBody: List<StatAST>, val elseBody: List<StatAST>) : StatAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        //cond is bool
        if (!cond.check(table)) {
            return false
        }
        val condType = cond.getRealType(table)
        if (condType != TypeInstance.boolTypeInstance) {
            semanticError("If condition must evaluate to a BOOL, but was actually $condType", ctx)
            return false
        }
        val thenST = SymbolTable(table)
        for (stat in thenBody) {
            if (!stat.check(thenST)) {break}
        }
        val elseST = SymbolTable(table)
        for (stat in elseBody) {
            if (!stat.check(elseST)) {break}
        }
        return true
    }

    override fun translate(): List<Instruction> {
        val instr = mutableListOf<Instruction>()
        val elseLabel = getNextLabel()
        val afterElseLabel = getNextLabel()

        instr.addAll(cond.translate())
        instr.add(CompareInstr(Condition.AL, Register.R4, null, 0))
        instr.add(BranchInstr(Condition.EQ, elseLabel,  false))

        thenBody.forEach { instr.addAll(it.translate()) }
        instr.add(BranchInstr(Condition.AL, afterElseLabel, false))

        instr.add(elseLabel)
        elseBody.forEach { instr.addAll(it.translate()) }

        return instr
    }
}