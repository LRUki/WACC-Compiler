package wacc.extension.optimization

import wacc.frontend.ast.AST
import wacc.frontend.ast.OptimisationVisitor
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
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
//        if (!assigned) {
//
//        }
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
        var exp1 = ast.expr1
        var exp2 = ast.expr2
        var changed = false
        if (ast.expr1 is IdentAST) {
            exp1 = visit(ast.expr1) as ExprAST
            changed = true
        }
        if (ast.expr2 is IdentAST) {
            exp2 = visit(ast.expr2) as ExprAST
            changed = true
        }
        if (changed) {
            val binOpExpr = BinOpExprAST(ast.binOp, exp1, exp2)
            binOpExpr.symTable = ast.symTable
            return binOpExpr
        }
        return ast
    }

    override fun visitUnOpExprAST(ast: UnOpExprAST): AST {
        if (ast.expr is IdentAST) {
            val unOpExpr = UnOpExprAST(ast.unOp, visit(ast.expr) as ExprAST)
            unOpExpr.symTable = ast.symTable
            return unOpExpr
        }
        return ast
    }

}