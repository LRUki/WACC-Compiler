package wacc.extension.optimisation

import wacc.frontend.ast.AST
import wacc.frontend.ast.expression.BoolLiterAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.ForStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST

class ControlFlowVisitor : OptimisationVisitor() {

    override fun visitIfStatAST(ast: IfStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        if (cond !is BoolLiterAST) {
//            throw RuntimeException("Condition is not a a Boolean value. Semantic check failed")
            return ast
        }

        val branchOfChoice = mutableListOf<StatAST>()
        /** Condition is true, then branch only */
        if (cond.value) {
            ast.thenBody.forEach { branchOfChoice.add(visit(it) as StatAST) }

        }
        /** Condition is false, else branch only */
        else if (!cond.value) {
            ast.elseBody.forEach { branchOfChoice.add(visit(it) as StatAST) }

        } else {
            throw RuntimeException("If condition was neither true or false. Semantic check failed")
        }
        return MultiStatAST(branchOfChoice)
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        if (cond !is BoolLiterAST) {
//            throw RuntimeException("Condition is not a a Boolean value. Semantic check failed")
            return ast
        }
        /** Condition is true, proceed as normal */
        if (cond.value) {
            return ast
        }
        /** Condition is false, skip over loop */
        if (!cond.value) {
            return SkipStatAST()
        } else {
            throw RuntimeException("If condition was neither true or false. Semantic check failed")
        }
    }

    override fun visitForStatAST(ast: ForStatAST): AST {
        TODO("Not yet implemented")
    }

}