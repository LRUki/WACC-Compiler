package wacc.extension.optimisation

import wacc.frontend.ast.AST
import wacc.frontend.ast.assign.*
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*

class ConstantEvaluationVisitor : OptimisationVisitor() {

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
        val rhs = visit(ast.rhs) as RhsAST
        val declareStatAST = DeclareStatAST(ast.type, ast.ident, rhs)
        ast.symTable.updateOptimisedVariable(ast.ident.name, rhs)
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
        ast.stats.forEach {
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
                    /** Evaluates ==, != for Booleans */
                    if (e1 is BoolLiterAST && e2 is BoolLiterAST) {
                        val v1 = e1.value
                        val v2 = e2.value
                        when (ast.binOp) {
                            CmpBinOp.EQ -> BoolLiterAST(v1 == v2)
                            CmpBinOp.NEQ -> BoolLiterAST(v1 != v2)
                            else -> ast
                        }
                    } else {
                        /** Evaluate <, <=, ==, !=, >, >=  */
                        val v1 = (e1 as IntLiterAST).value
                        val v2 = (e2 as IntLiterAST).value
                        when (ast.binOp) {
                            CmpBinOp.EQ -> BoolLiterAST(v1 == v2)
                            CmpBinOp.NEQ -> BoolLiterAST(v1 != v2)
                            CmpBinOp.GT -> BoolLiterAST(v1 > v2)
                            CmpBinOp.GTE -> BoolLiterAST(v1 >= v2)
                            CmpBinOp.LT -> BoolLiterAST(v1 < v2)
                            CmpBinOp.LTE -> BoolLiterAST(v1 <= v2)
                        }
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
        when {
            /** evaluate not */
            (e1 is BoolLiterAST) and (ast.unOp == UnOp.NOT) -> {
                return BoolLiterAST(!(e1 as BoolLiterAST).value)
            }
            /** evaluate chr */
            (e1 is IntLiterAST) and (ast.unOp == UnOp.CHR) -> {
                return CharLiterAST((e1 as IntLiterAST).value.toChar())
            }
            /** evaluate ord */
            (e1 is CharLiterAST) and (ast.unOp == UnOp.ORD) -> {
                return IntLiterAST((e1 as CharLiterAST).value.toInt())
            }
            /** evaluate minus */
            (e1 is IntLiterAST) and (ast.unOp == UnOp.MINUS) -> {
                return IntLiterAST(-(e1 as IntLiterAST).value.toInt())
            }
            else -> return ast
        }
    }

    override fun visitStructAssignAST(ast: StructAssignAST): AST {
        val assignments = mutableListOf<RhsAST>()
        ast.assignments.forEach {
            assignments.add(visit(it) as RhsAST)
        }
        val structAssign = StructAssignAST(assignments)
        structAssign.symTable = ast.symTable
        structAssign.symTable = ast.symTable
        return structAssign
    }
}