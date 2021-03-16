package wacc.extension.optimization

import wacc.frontend.ast.AST
import wacc.frontend.ast.OptimisationVisitor
import wacc.frontend.ast.expression.BoolLiterAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ImportAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.CallStatAST

class ControlFlowVisitor : OptimisationVisitor {

    override fun visitProgramAST(ast: ProgramAST): AST {
        val importList = mutableListOf<ImportAST>()
        ast.imports.forEach {
            importList.add(visit(it) as ImportAST)
        }
        val funcList = mutableListOf<FuncAST>()
        ast.funcList.forEach {
            funcList.add(visit(it) as FuncAST)
        }
        val stats = mutableListOf<StatAST>()
        ast.stats.forEach {
            stats.add(visit(it) as StatAST)
        }
        val programAST = ProgramAST(importList, stats, funcList)
        programAST.symTable = ast.symTable
        return programAST
    }

    override fun visitFuncAST(ast: FuncAST): AST {
        val body = mutableListOf<StatAST>()
        ast.body.forEach {
            body.add(visit(it) as StatAST)
        }
        return ast
    }

    override fun visitParamAST(ast: ParamAST): AST {
        //todo
        return ast
    }

    override fun visitBlockStatAST(ast: BlockStatAST): AST {
        val body = mutableListOf<StatAST>()
        ast.body.forEach {
            body.add(visit(it) as StatAST)
        }
        val blockStatAST = BlockStatAST(body)
        blockStatAST.symTable = ast.symTable
        return blockStatAST
    }

    override fun visitIfStatAST(ast: IfStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        if (cond !is BoolLiterAST) {
            throw RuntimeException("Condition is not a a Boolean value. Semantic check failed")
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
            throw RuntimeException("Condition is not a a Boolean value. Semantic check failed")
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

}