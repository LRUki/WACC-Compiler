package wacc.extension

import wacc.frontend.ast.AST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.ActionStatAST
import wacc.frontend.ast.statement.nonblock.AssignStatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.statement.nonblock.ReadStatAST
import wacc.frontend.ast.type.TypeAST

class OptimizeVisitor: AstVisitor<AST> {
    override fun visitProgramAST(ast: ProgramAST): AST {
        val funcList = mutableListOf<FuncAST>()
        ast.funcList.forEach{
            funcList.add( visit(it) as FuncAST)
        }
        val stats = mutableListOf<StatAST>()
        ast.stats.forEach{
            stats.add(visit(it) as StatAST)
        }
        return ProgramAST(funcList, stats)
    }

    override fun visitFuncAST(ast: FuncAST): AST {
        val body = mutableListOf<StatAST>()
        ast.body.forEach{
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
        ast.body.forEach{
            body.add(visit(it) as StatAST)
        }
        return BlockStatAST(body)
    }

    override fun visitIfStatAST(ast: IfStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        val thenBody = mutableListOf<StatAST>()
        ast.thenBody.forEach { thenBody.add(visit(it) as StatAST) }


        val elseBody = mutableListOf<StatAST>()
        ast.elseBody.forEach { elseBody.add(visit(it) as StatAST) }

        return IfStatAST(cond, thenBody, elseBody)
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val cond = visit(ast.cond) as ExprAST
        val body = mutableListOf<StatAST>()
        ast.body.forEach{
            body.add(visit(it) as StatAST)
        }
        return WhileStatAST(cond ,body)
    }

    override fun visitActionStatAST(ast: ActionStatAST): AST {
        return ActionStatAST(ast.action, visit(ast.expr) as ExprAST)
    }

    override fun visitAssignStatAST(ast: AssignStatAST): AST {
        //todo just right hand side?
        return AssignStatAST(ast.lhs, visit(ast.rhs) as RhsAST)
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        return DeclareStatAST(ast.type,ast.ident,visit(ast.rhs) as ExprAST)
    }

    override fun visitReadStatAST(ast: ReadStatAST): AST {
        return ReadStatAST(visit(ast.expr) as LhsAST)
    }

    override fun visitSkipStatAST(ast: SkipStatAST): AST {
        return ast
    }

    override fun visitMultiStatAST(ast: MultiStatAST): AST {
        val stats = mutableListOf<StatAST>()
        ast.stats.forEach{
            stats.add(visit(it) as StatAST)
        }
        return MultiStatAST(stats)
    }

    override fun visitNewPairRhsAST(ast: NewPairRhsAST): AST {
        val newPairAst = NewPairRhsAST(visit(ast.fst) as ExprAST,
                visit(ast.snd) as ExprAST)
        newPairAst.firstType = ast.firstType
        newPairAst.secondType = ast.secondType
        return newPairAst
    }

    override fun visitCallRhsAST(ast: CallRhsAST): AST {
        TODO("arglist could be constant?")
    }

    override fun visitBinOpExprAST(ast: BinOpExprAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitUnOpExprAST(ast: UnOpExprAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitArrayElemAST(ast: ArrayElemAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitPairElemAST(ast: PairElemAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitIdentAST(ast: IdentAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitIntLiterAST(ast: IntLiterAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitBoolLiterAST(ast: BoolLiterAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitStrLiterAST(ast: StrLiterAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitCharLiterAST(ast: CharLiterAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitNullPairLiterAST(ast: NullPairLiterAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitArrayLiterAST(ast: ArrayLiterAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitTypeAST(ast: TypeAST): AST {
        TODO("Not yet implemented")
    }
}