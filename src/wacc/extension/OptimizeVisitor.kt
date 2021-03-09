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
import wacc.frontend.ast.type.TypeInstance
import wacc.frontend.exception.semanticError
import java.lang.RuntimeException

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
        val programAST = ProgramAST(funcList, stats)
        programAST.symTable = ast.symTable
        return programAST
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
        val blockStatAST = BlockStatAST(body)
        blockStatAST.symTable = ast.symTable
        return blockStatAST
    }

    override fun visitIfStatAST(ast: IfStatAST): AST {
        val cond = visit(ast.cond) as ExprAST

        val thenBody = mutableListOf<StatAST>()
        ast.thenBody.forEach { thenBody.add(visit(it) as StatAST) }


        val elseBody = mutableListOf<StatAST>()
        ast.elseBody.forEach { elseBody.add(visit(it) as StatAST) }

        val ifStatAST = IfStatAST(cond, thenBody, elseBody)
        ifStatAST.symTable = ast.symTable
        ifStatAST.thenST = ast.thenST
        ifStatAST.elseST = ast.elseST
        ifStatAST.thenHasReturn = ast.thenHasReturn
        ifStatAST.elseHasReturn = ast.elseHasReturn

        return ifStatAST
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        val cond = visit(ast.cond) as ExprAST
        val body = mutableListOf<StatAST>()
        ast.body.forEach{
            body.add(visit(it) as StatAST)
        }
        val whileStatAST = WhileStatAST(cond ,body)
        whileStatAST.symTable = ast.symTable
        whileStatAST.blockST = ast.blockST
        return whileStatAST
    }

    override fun visitActionStatAST(ast: ActionStatAST): AST {
        val actionStatAST = ActionStatAST(ast.action, visit(ast.expr) as ExprAST)
        actionStatAST.symTable = ast.symTable
        return actionStatAST
    }

    override fun visitAssignStatAST(ast: AssignStatAST): AST {
        //todo just right hand side?
        val assignStatAst = AssignStatAST(ast.lhs, visit(ast.rhs) as RhsAST)
        assignStatAst.symTable = ast.symTable
        return assignStatAst
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        val declareStatAST = DeclareStatAST(ast.type,ast.ident,visit(ast.rhs) as RhsAST)
        declareStatAST.symTable = ast.symTable
        return declareStatAST
    }

    override fun visitReadStatAST(ast: ReadStatAST): AST {
        val readStatAST = ReadStatAST(visit(ast.expr) as LhsAST)
        readStatAST.symTable = ast.symTable
        readStatAST.exprType = ast.exprType
        return readStatAST
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
        //TODO arglist could be constant?
        val argList = mutableListOf<ExprAST>()
        ast.argList.forEach { argList.add(visit(it) as ExprAST) }
        val callRhsAST = CallRhsAST(ast.ident, argList)
        callRhsAST.symTable = ast.symTable
        callRhsAST.argTypes = ast.argTypes
        return callRhsAST
    }


    override fun visitBinOpExprAST(ast: BinOpExprAST): AST {
        return  when (ast.binOp) {
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                if ((visit(ast.expr1) !is IntLiterAST)
                        or (visit(ast.expr2) !is IntLiterAST)){
                    return ast
                }
                val val1 = (visit(ast.expr1) as IntLiterAST).value
                val val2 = (visit(ast.expr2) as IntLiterAST).value
                when (ast.binOp){
                    BinOp.PLUS -> IntLiterAST(val1 + val2)
                    BinOp.MINUS -> IntLiterAST(val1 - val2)
                    BinOp.MULT -> IntLiterAST(val1 * val2)
                    BinOp.DIV -> IntLiterAST(val1 / val2)
                    BinOp.MOD -> if(val2 != 0) IntLiterAST(val1 % val2) else ast
                    else -> throw RuntimeException("wrong operand")
                }
            }
            else -> ast
        }
    }

    override fun visitUnOpExprAST(ast: UnOpExprAST): AST {
        return ast
    }

    override fun visitArrayElemAST(ast: ArrayElemAST): AST {
        return ast
    }

    override fun visitPairElemAST(ast: PairElemAST): AST {
        return ast
    }

    override fun visitIdentAST(ast: IdentAST): AST {
        return ast
    }

    override fun visitIntLiterAST(ast: IntLiterAST): AST {
        return ast
    }

    override fun visitBoolLiterAST(ast: BoolLiterAST): AST {
        return ast
    }

    override fun visitStrLiterAST(ast: StrLiterAST): AST {
        return ast
    }

    override fun visitCharLiterAST(ast: CharLiterAST): AST {
        return ast
    }

    override fun visitNullPairLiterAST(ast: NullPairLiterAST): AST {
        return ast
    }

    override fun visitArrayLiterAST(ast: ArrayLiterAST): AST {
        return ast
    }

    override fun visitTypeAST(ast: TypeAST): AST {
        return ast
    }
}