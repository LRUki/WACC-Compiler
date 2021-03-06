package wacc.extension.optimisation

import wacc.frontend.ast.AST
import wacc.frontend.ast.AbstractAST
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
            return ast
        }

        val branchOfChoice = mutableListOf<StatAST>()
        /** Condition is true, then branch only */
        if (cond.value) {
            if (ast.thenST.checkContainsSameVarNameAsEnclosing()) {
                return ast
            }
            ast.thenBody.forEach {
                val item = visit(it) as StatAST
                branchOfChoice.add(item)
                if (item is AbstractAST) {
                    item.symTable = ast.symTable
                }
            }
            ast.thenST.liftToUpperScope()

        } else {
            /** Condition is false, else branch only */
            if (ast.elseST.checkContainsSameVarNameAsEnclosing()) {
                return ast
            }
            ast.elseBody.forEach {
                branchOfChoice.add(visit(it) as StatAST)
                if (it is AbstractAST) {
                    it.symTable = ast.symTable
                }
            }
            ast.elseST.liftToUpperScope()
        }


        return MultiStatAST(branchOfChoice)
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        if (cond !is BoolLiterAST) {
            return ast
        }
        /** Condition is true, proceed as normal */
        if (cond.value) {
            return ast
        }
        /** Condition is false, skip over loop */
        return SkipStatAST()
    }

    override fun visitForStatAST(ast: ForStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        if (cond !is BoolLiterAST) {
            return ast
        }
        /** Condition is true, proceed as normal */
        if (cond.value) {
            return ast
        }
        /** Condition is false, skip over loop */
        return SkipStatAST()
    }

}