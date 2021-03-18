package wacc.frontend.ast

import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.StructAssignAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.ForStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.type.TypeAST

abstract class OptimisationVisitor : AstVisitor<AST> {
    /*
     * Visit program and function ASTs.
     */
    override fun visitProgramAST(ast: ProgramAST): AST {
        return ast
    }

    override fun visitFuncAST(ast: FuncAST): AST {
        return ast
    }

    override fun visitParamAST(ast: ParamAST): AST {
        return ast
    }

    /*
     * Visit statement ASTs.
     */
    override fun visitBlockStatAST(ast: BlockStatAST): AST {
        return ast
    }

    override fun visitIfStatAST(ast: IfStatAST): AST {
        return ast
    }

    override fun visitWhileStatAST(ast: WhileStatAST): AST {
        return ast
    }

    override fun visitForStatAST(ast: ForStatAST): AST {
        return ast
    }

    override fun visitActionStatAST(ast: ActionStatAST): AST {
        return ast
    }

    override fun visitVoidReturnStatAST(ast: VoidReturnStatAST): AST {
        return ast
    }

    override fun visitAssignStatAST(ast: AssignStatAST): AST {
        return ast
    }

    override fun visitDeclareStatAST(ast: DeclareStatAST): AST {
        return ast
    }

    override fun visitReadStatAST(ast: ReadStatAST): AST {
        return ast
    }

    override fun visitSkipStatAST(ast: SkipStatAST): AST {
        return ast
    }

    override fun visitMultiStatAST(ast: MultiStatAST): AST {
        return ast
    }

    /*
     * Visit RHS ASTs.
     */
    override fun visitNewPairRhsAST(ast: NewPairRhsAST): AST {
        return ast
    }

    override fun visitCallRhsAST(ast: CallRhsAST): AST {
        return ast
    }

    /*
     * Visit expression ASTs.
     */
    override fun visitBinOpExprAST(ast: BinOpExprAST): AST {
        return ast
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

    override fun visitPointerElemAST(ast: PointerElemAST): AST {
        return ast
    }

    /*
     * Visit ident and literal ASTs.
     */
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

    /*
     * Visit type ASTs.
     */
    override fun visitTypeAST(ast: TypeAST): AST {
        return ast
    }

    /*
    * Visit Struct ASTs.
    */
    override fun visitStructAccessAST(ast: StructAccessAST): AST {
        return ast
    }

    override fun visitStructDeclareAST(ast: StructDeclareAST): AST {
        return ast
    }

    override fun visitStructAssignAST(ast: StructAssignAST): AST {
        return ast
    }

    override fun visitCallStatAST(ast: CallStatAST): AST {
        return ast
    }
}