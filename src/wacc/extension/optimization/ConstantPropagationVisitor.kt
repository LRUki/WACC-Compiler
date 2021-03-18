package wacc.extension.optimization

import wacc.frontend.ast.AST
import wacc.frontend.ast.OptimisationVisitor
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.assign.StructAssignAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.Action
import wacc.frontend.ast.statement.nonblock.ActionStatAST
import wacc.frontend.ast.statement.nonblock.AssignStatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST

class ConstantPropagationVisitor : OptimisationVisitor() {
    override fun visitProgramAST(ast: ProgramAST): AST {
        val funcList = mutableListOf<FuncAST>()
        ast.funcList.forEach {
            funcList.add(visit(it) as FuncAST)
        }
        val stats = mutableListOf<StatAST>()
        ast.stats.forEach {
            stats.add(visit(it) as StatAST)
        }
        val programAST = ProgramAST(ast.imports, stats, funcList)
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
        //TODO
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



    override fun visitIdentAST(ast: IdentAST): AST {
        val assigned = ast.symTable.getAssignedField(ast.name)
        if (!assigned) {
            val entry = ast.symTable.lookupAll(ast.name).get()
            if (entry is DeclareStatAST) {
                return entry.rhs
            }
        }
        return ast
    }

    override fun visitStructAccessAST(ast: StructAccessAST): AST {
        val assigned = ast.symTable.getAssignedField(ast.structIdent.name)
        if (!assigned) {
            var rhs: RhsAST? = null
            val entry = ast.symTable.lookupAll(ast.structIdent.name).get()
            val assignments = ((entry as DeclareStatAST).rhs as StructAssignAST).assignments
            for (i in ast.structDeclare.fields.indices) {
                if (ast.structDeclare.fields[i].ident.equals(ast.fieldIdent)) {
                    rhs = assignments[i]
                    break
                }
            }
            if (rhs != null) {
                return rhs
            }
        }
        return ast
    }

    override fun visitActionStatAST(ast: ActionStatAST): AST {
        if (ast.expr is IdentAST && (ast.action == Action.EXIT || ast.action == Action.RETURN)) {
            val actionStat = ActionStatAST(ast.action, visit(ast.expr) as ExprAST)
            actionStat.symTable = ast.symTable
            return actionStat
        }
        return ast
    }


    override fun visitAssignStatAST(ast: AssignStatAST): AST {
        if (ast.lhs is IdentAST) {
            val rhs = visit(ast.rhs) as RhsAST
            if (ast.rhs != rhs) {
                val assignStat = AssignStatAST(ast.lhs, rhs)
                ast.symTable.updateOptimisedVariable(ast.lhs.name, rhs)
                assignStat.symTable = ast.symTable
                return assignStat
            }
        }
        return ast
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        val rhs = visit(ast.rhs) as RhsAST
        if (rhs != ast.rhs) {
            val declareStat = DeclareStatAST(ast.type, ast.ident, rhs)
            declareStat.symTable = ast.symTable
            ast.symTable.updateOptimisedVariable(ast.ident.name, rhs)
            return declareStat
        }
        return ast
    }

    override fun visitBinOpExprAST(ast: BinOpExprAST): AST {
        val exp1 = visit(ast.expr1) as ExprAST
        val exp2 = visit(ast.expr2) as ExprAST
        if (exp1 != ast.expr1 || exp2 != ast.expr2) {
            val binOpExpr = BinOpExprAST(ast.binOp, exp1, exp2)
            binOpExpr.symTable = ast.symTable
            return binOpExpr
        }
        return ast
    }

    override fun visitUnOpExprAST(ast: UnOpExprAST): AST {
        if (ast.expr is IdentAST) {
            val expr = visit(ast.expr) as ExprAST
            if (expr != ast.expr && ast.unOp != UnOp.REF) {
                val unOpExpr = UnOpExprAST(ast.unOp, expr)
                unOpExpr.symTable = ast.symTable
                return unOpExpr
            }
        }
        return ast
    }
    override fun visitIfStatAST(ast: IfStatAST): AST {
        val ifCond = visit(ast.cond) as ExprAST
        val thenStats = mutableListOf<StatAST>()
        val elseStats = mutableListOf<StatAST>()
        ast.thenBody.forEach { thenStats.add(visit(it) as StatAST) }
        ast.elseBody.forEach { elseStats.add(visit(it) as StatAST) }
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
        ast.body.forEach{
            bodyStats.add(visit(it) as StatAST)
        }
        val whileAST = WhileStatAST(whileCond, bodyStats)
        whileAST.symTable = ast.symTable
        whileAST.blockST = ast.blockST
        return whileAST
    }
}
