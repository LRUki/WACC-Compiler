package wacc.extension.optimization

import antlr.WaccParser
import org.antlr.v4.runtime.ParserRuleContext
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.LoadInstr
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.MemoryType
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.RegisterAddrWithOffsetMode
import wacc.frontend.ast.AST
import wacc.frontend.ast.OptimisationVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.program.ImportAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.nonblock.ActionStatAST
import wacc.frontend.ast.statement.nonblock.AssignStatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import java.util.function.BinaryOperator

class ConstantPropagationVisitor : OptimisationVisitor() {
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


    //    visitBla(){
//        visit(ident)
//        return ast
//    }
    override fun visitIdentAST(ast: IdentAST): AST {
        val identInST = ast.symTable.getAccessField(ast.name)
        if (!identInST) {
            val entry = ast.symTable.lookupAll(ast.name).get()
            if (entry is DeclareStatAST) {
                ast.symTable.updateConstPropVariable(ast.name, entry)
                return entry.rhs
            }
        }
        return ast
    }

    override fun visitActionStatAST(ast: ActionStatAST): AST {
        if (ast.expr is IdentAST) {
            val actionStat = ActionStatAST(ast.action, visit(ast.expr) as ExprAST)
            actionStat.symTable = ast.symTable
            return actionStat
        }
        return ast
    }

    override fun visitAssignStatAST(ast: AssignStatAST): AST {
        if (ast.lhs is IdentAST) {
            val assignStat = AssignStatAST(visit(ast.lhs) as IdentAST, ast.rhs)
            assignStat.symTable = ast.symTable
            return assignStat
        }
        return ast
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        val declareStat = DeclareStatAST(ast.type, ast.ident, visit(ast.rhs) as RhsAST)
        declareStat.symTable = ast.symTable
        return declareStat
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

//    override fun visitArrayElemAST(ast: ArrayElemAST): AST {
//        val arrayElemAST = ArrayElemAST(visit(ast.ident) as IdentAST, ast.indices)
//        arrayElemAST.symTable = ast.symTable
//        return arrayElemAST
//    }
}