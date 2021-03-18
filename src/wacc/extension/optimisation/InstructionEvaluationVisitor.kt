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

    override fun visitStat(item: AST, list: MutableList<StatAST>) {
        val stat = visit(item)
        if (stat !is SkipStatAST) {
            list.add(stat as StatAST)
        }
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val whileCond = visit(ast.cond) as ExprAST
        val bodyStats = mutableListOf<StatAST>()
        ast.body.forEach { visitStat(it, bodyStats) }
        if (bodyStats.isEmpty()) {
            return SkipStatAST()
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
            ast.symTable.removeOptimisedVariableFromST(ast.name)
            return SkipStatAST()
        }
        return ast
    }


}