package wacc.extension.optimization

import wacc.frontend.ast.AST
import wacc.frontend.ast.OptimisationVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.*
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.program.ImportAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.ForStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.type.TypeAST

class ConstantEvaluationVisitor: OptimisationVisitor() {
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
        ast.body.forEach{
            body.add(visit(it) as StatAST)
        }
        return ast
    }

    override fun visitParamAST(ast: ParamAST): AST {
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
        val argList = mutableListOf<ExprAST>()
        ast.argList.forEach { argList.add(visit(it) as ExprAST) }
        val callRhsAST = CallRhsAST(ast.ident, argList)
        callRhsAST.symTable = ast.symTable
        callRhsAST.argTypes = ast.argTypes
        return callRhsAST
    }

    override fun visitBinOpExprAST(ast: BinOpExprAST): AST {
        val e1 = visit(ast.expr1)
        val e2 = visit(ast.expr2)

        if (((e1 is IntLiterAST) and (e2 is IntLiterAST)) or
                ((e1 is BoolLiterAST) and (e2 is BoolLiterAST))) {

            return when (ast.binOp) {
                /** evaluate ||, && */
                is BoolBinOp ->
                    BoolLiterAST((ast.binOp).apply((e1 as BoolLiterAST).value, (e2 as BoolLiterAST).value))
                /** evaluate +, -, *, /, % */
                is IntBinOp -> {
                    val v1 = (e1 as IntLiterAST).value
                    val v2 = (e2 as IntLiterAST).value
                    if ((ast.binOp == IntBinOp.DIV) or (ast.binOp == IntBinOp.MOD) and (v2 == 0)) {
                        return ast
                    }
                    return IntLiterAST(ast.binOp.apply(v1, v2))
                }
                is CmpBinOp -> {
                    val v1 = (e1 as IntLiterAST).value
                    val v2 = (e2 as IntLiterAST).value
                    /** evaluate <, <=, ==, !=, >, >=  */
                    when (ast.binOp) {
                        CmpBinOp.EQ -> BoolLiterAST(v1 == v2)
                        CmpBinOp.NEQ -> BoolLiterAST(v1 != v2)
                        CmpBinOp.GT -> BoolLiterAST(v1 > v2)
                        CmpBinOp.GTE -> BoolLiterAST(v1 >= v2)
                        CmpBinOp.LT -> BoolLiterAST(v1 < v2)
                        CmpBinOp.LTE -> BoolLiterAST(v1 <= v2)
                    }
                }
                else -> ast
            }
        } else {
            return ast
        }
    }


    override fun visitUnOpExprAST(ast: UnOpExprAST): AST {
        val e1 = visit(ast.expr)
        if ((e1 is BoolLiterAST) and (ast.unOp == UnOp.NOT)) {
            /** evaluate not */
            return BoolLiterAST(!(e1 as BoolLiterAST).value)
        } else if ((e1 is IntLiterAST) and (ast.unOp == UnOp.CHR)) {
            /** evaluate chr */
            return CharLiterAST((e1 as IntLiterAST).value.toChar())
        } else if ((e1 is CharLiterAST) and (ast.unOp == UnOp.ORD)) {
            /** evaluate ord */
            return IntLiterAST((e1 as CharLiterAST).value.toInt())
        }
        return ast
    }
}