package wacc.frontend.ast.statement.block

import wacc.backend.CodeGenerator.freeAllCalleeReg
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextLabel
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
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
            if (!stat.check(thenST)) {
                break
            }
        }
        elseST = SymbolTable(table)
        for (stat in elseBody) {
            if (!stat.check(elseST)) {
                break
            }
        }
        return true
    }


    override fun translate(): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val elseLabel = getNextLabel()
        val afterElseLabel = getNextLabel()

        instrs.addAll(cond.translate())
        instrs.add(CompareInstr(seeLastUsedCalleeReg(), ImmediateIntOperand(0)))
        instrs.add(BranchInstr(Condition.EQ, elseLabel, false))
        freeCalleeReg()
        var stackOffset = thenST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        thenBody.forEach {
            instrs.addAll(it.translate())
        }
        val lastStat = thenBody.last()
        if ((lastStat is ActionStatAST) && lastStat.action == Action.RETURN) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(symTable.getFuncStackOffset())))
            instrs.addAll(regsToPopInstrs(listOf(Register.PC)))
            freeAllCalleeReg()
        }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }

        instrs.add(BranchInstr(Condition.AL, afterElseLabel, false))
        instrs.add(elseLabel)

        stackOffset = elseST.getStackOffset()
        if (stackOffset > 0) {
            instrs.add(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        elseBody.forEach { instrs.addAll(it.translate()) }
        if (stackOffset > 0) {
            instrs.add(AddInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(stackOffset)))
        }
        instrs.add(afterElseLabel)
        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitIfStatAST(this)
    }

}