package wacc.extension.optimisation

import wacc.frontend.ast.AST
import wacc.frontend.ast.assign.CallRhsAST
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
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.PairTypeAST
import wacc.frontend.ast.type.StructTypeAST

class ConstantPropagationVisitor : OptimisationVisitor() {

    override fun visitIdentAST(ast: IdentAST): AST {
        val assigned = ast.symTable.getAssignedField(ast.name)
        if (!assigned) {
            val entry = ast.symTable.lookupAll(ast.name).get()
            if (entry is ParamAST) {
                return ast
            }
            if (entry is DeclareStatAST) {
                if (entry.type is StructTypeAST ||
                        entry.type is ArrayTypeAST || entry.type is PairTypeAST) {
                    return ast
                }
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
        if (ast.expr is IdentAST && (ast.action != Action.FREE)) {
//                (ast.action == Action.EXIT || ast.action == Action.RETURN)) {
            val exprType = ast.expr.getRealType(ast.symTable)
            if (exprType is PairTypeAST) {
                return ast
            }
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
}
