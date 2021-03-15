package wacc.extension.optimization

import wacc.frontend.ast.AST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.StructAssignAST
import wacc.frontend.ast.assign.StructFieldAssignAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.type.TypeAST

class ControlFlowVisitor : AstVisitor<AST> {
    override fun visitIfStatAST(ast: IfStatAST): AST {
        val cond = visit(ast.cond) as ExprAST
        val trueLiter = BoolLiterAST(true)
        val falseLiter = BoolLiterAST(false)

//        val branchOfChoice =
        /** Condition is true, then branch only */
        if (cond == trueLiter) {
            val thenBody = mutableListOf<StatAST>()
            ast.thenBody.forEach { thenBody.add(visit(it) as StatAST) }

            return MultiStatAST(thenBody)
        }
        /** Condition is false, else branch only */
        if (cond == falseLiter) {
            val elseBody = mutableListOf<StatAST>()
            ast.elseBody.forEach { elseBody.add(visit(it) as StatAST) }

            return MultiStatAST(elseBody)
        } else {
            throw RuntimeException("Bool liter is neither true or false. This should not happen")
        }
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        TODO("Not yet implemented")
        //cond true just normal
        //cond false nothing
    }


    override fun visitProgramAST(ast: ProgramAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitFuncAST(ast: FuncAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitParamAST(ast: ParamAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitBlockStatAST(ast: BlockStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitActionStatAST(ast: ActionStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitAssignStatAST(ast: AssignStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitReadStatAST(ast: ReadStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitSkipStatAST(ast: SkipStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitMultiStatAST(ast: MultiStatAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitNewPairRhsAST(ast: NewPairRhsAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitCallRhsAST(ast: CallRhsAST): AST {
        TODO("Not yet implemented")
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

    override fun visitPointerElemAST(ast: PointerElemAST): AST {
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

    override fun visitStructAccessAST(ast: StructAccessAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitStructDeclareAST(ast: StructDeclareAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitStructAssignAST(ast: StructAssignAST): AST {
        TODO("Not yet implemented")
    }

    override fun visitStructFieldAssignAST(ast: StructFieldAssignAST): AST {
        TODO("Not yet implemented")
    }

}