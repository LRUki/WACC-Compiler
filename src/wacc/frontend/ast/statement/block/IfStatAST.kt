package wacc.frontend.ast.statement.block

import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextLabel
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.*
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.ImmediateOperandInt
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.nonblock.Action
import wacc.frontend.ast.statement.nonblock.ActionStatAST
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
    lateinit var thenST: SymbolTable
    lateinit var elseST: SymbolTable

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
        thenST = SymbolTable(table)
        for (stat in thenBody) {
            if (!stat.check(thenST)) {break}
        }
        elseST = SymbolTable(table)
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
        instr.add(CompareInstr(seeLastUsedCalleeReg(), ImmediateOperandInt(0)))
        instr.add(BranchInstr(Condition.EQ, elseLabel,  false))
        freeCalleeReg()
        var stackOffset = thenST.getStackOffset()
        if (stackOffset > 0) {
            instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        thenBody.forEach {
            instr.addAll(it.translate())
        }
        val lastStat = thenBody.last()
        if((lastStat is ActionStatAST) && lastStat.action == Action.RETURN){
            instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(symTable.getFuncStackOffset())))
            instr.addAll(regsToPopInstrs(listOf(Register.PC)))
        }
        if (stackOffset > 0) {
            instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }

        instr.add(BranchInstr(Condition.AL, afterElseLabel, false))
        instr.add(elseLabel)

        stackOffset = elseST.getStackOffset()
        if (stackOffset > 0) {
            instr.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        elseBody.forEach { instr.addAll(it.translate()) }
        if (stackOffset > 0) {
            instr.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateOperandInt(stackOffset)))
        }
        instr.add(afterElseLabel)
        return instr
    }
}