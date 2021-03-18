package wacc.extension.optimisation

import wacc.frontend.ast.AST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.AssignStatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST

class InstructionEvaluationVisitor : OptimisationVisitor() {

    override fun visitProgramAST(ast: ProgramAST): AST {
        val funcList = mutableListOf<FuncAST>()
        ast.funcList.forEach {
            funcList.add(visit(it) as FuncAST)
        }
        val stats = mutableListOf<StatAST>()
        ast.stats.forEach {
            val stat = visit(it)
            if (stat !is SkipStatAST) {
                stats.add(stat as StatAST)
            }
        }
        val programAST = ProgramAST(ast.imports, stats, funcList)
        programAST.symTable = ast.symTable
        return programAST
    }

    override fun visitFuncAST(ast: FuncAST): AST {
        val body = mutableListOf<StatAST>()
        ast.body.forEach {
            val stat = visit(it)
            if (stat !is SkipStatAST) {
                body.add(stat as StatAST)
            }
        }
        return ast
    }

    override fun visitBlockStatAST(ast: BlockStatAST): AST {
        val body = mutableListOf<StatAST>()
        ast.body.forEach {
            val stat = visit(it)
            if (stat !is SkipStatAST) {
                body.add(stat as StatAST)
            }
        }
        val blockStatAST = BlockStatAST(body)
        blockStatAST.symTable = ast.symTable
        return blockStatAST
    }

    override fun visitIfStatAST(ast: IfStatAST): AST {
        val ifCond = visit(ast.cond) as ExprAST
        val thenStats = mutableListOf<StatAST>()
        val elseStats = mutableListOf<StatAST>()
        ast.thenBody.forEach {
            val stat = visit(it)
            if (stat !is SkipStatAST) {
                thenStats.add(stat as StatAST)
            }
        }
        ast.elseBody.forEach {
            val stat = visit(it)
            if (stat !is SkipStatAST) {
                elseStats.add(stat as StatAST)
            }
        }
        val ifStatAst = IfStatAST(ifCond, thenStats, elseStats)
        ifStatAst.symTable = ast.symTable
        ifStatAst.thenST = ast.thenST
        ifStatAst.elseST = ast.elseST
        ifStatAst.thenHasReturn = ast.thenHasReturn
        ifStatAst.elseHasReturn = ast.elseHasReturn
        return ifStatAst
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val whileCond = visit(ast.cond) as ExprAST
        val bodyStats = mutableListOf<StatAST>()
        ast.body.forEach {
            val stat = visit(it)
            if (stat !is SkipStatAST) {
                bodyStats.add(stat as StatAST)
            }
        }
        val whileAST = WhileStatAST(whileCond, bodyStats)
        whileAST.symTable = ast.symTable
        whileAST.blockST = ast.blockST
        return whileAST
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        val identAst = visit(ast.ident)
        if (identAst is SkipStatAST) {
            return SkipStatAST()
        }
        return ast
    }

    override fun visitIdentAST(ast: IdentAST): AST {
        val accessed = ast.symTable.getAccessedField(ast.name)
        if (!accessed) {
            return SkipStatAST()
        }
        return ast
    }


}