package wacc.extension.optimization

import wacc.frontend.ast.AST
import wacc.frontend.ast.OptimisationVisitor
import wacc.frontend.ast.expression.BoolLiterAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST

class ControlFlowVisitor : OptimisationVisitor {
    override fun visitIfStatAST(ast: IfStatAST): AST {
        val cond = visit(ast.cond) as ExprAST
        val trueLiter = BoolLiterAST(true)
        val falseLiter = BoolLiterAST(false)

        val branchOfChoice = mutableListOf<StatAST>()
        /** Condition is true, then branch only */
        if (cond == trueLiter) {
            ast.thenBody.forEach { branchOfChoice.add(visit(it) as StatAST) }

        }
        /** Condition is false, else branch only */
        if (cond == falseLiter) {
            ast.elseBody.forEach { branchOfChoice.add(visit(it) as StatAST) }

        } else {
            throw RuntimeException("If condition was neither true or false. Semantic check failed")
        }
        return MultiStatAST(branchOfChoice)
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val cond = visit(ast.cond) as ExprAST
        val trueLiter = BoolLiterAST(true)
        val falseLiter = BoolLiterAST(false)
        /** Condition is true, proceed as normal */
        if (cond == trueLiter) {
            return ast
        }
        /** Condition is false, skip over loop */
        if (cond == falseLiter) {
            return SkipStatAST()
        } else {
            throw RuntimeException("If condition was neither true or false. Semantic check failed")
        }
    }

}