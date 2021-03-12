package wacc.frontend.ast

import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.ActionStatAST
import wacc.frontend.ast.statement.nonblock.AssignStatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.statement.nonblock.ReadStatAST
import wacc.frontend.ast.type.TypeAST

interface AstVisitor<T> {

    fun visit(ast: AST): T {
        return ast.accept(this)
    }

    /*
     * Visit program and function ASTs.
     */
    fun visitProgramAST(ast: ProgramAST): T

    fun visitFuncAST(ast: FuncAST): T
    fun visitParamAST(ast: ParamAST): T

    /*
     * Visit statement ASTs.
     */
    fun visitBlockStatAST(ast: BlockStatAST): T
    fun visitIfStatAST(ast: IfStatAST): T
    fun visitWhileStatAST(ast: WhileStatAST): T

    fun visitActionStatAST(ast: ActionStatAST): T
    fun visitAssignStatAST(ast: AssignStatAST): T
    fun visitDeclareStatAST(ast: DeclareStatAST): T
    fun visitReadStatAST(ast: ReadStatAST): T
    fun visitSkipStatAST(ast: SkipStatAST): T
    fun visitMultiStatAST(ast: MultiStatAST): T

    /*
     * Visit RHS ASTs.
     */
    fun visitNewPairRhsAST(ast: NewPairRhsAST): T
    fun visitCallRhsAST(ast: CallRhsAST): T

    /*
     * Visit expression ASTs.
     */
    fun visitBinOpExprAST(ast: BinOpExprAST): T
    fun visitUnOpExprAST(ast: UnOpExprAST): T
    fun visitArrayElemAST(ast: ArrayElemAST): T
    fun visitPairElemAST(ast: PairElemAST): T
    fun visitPointerElemAST(ast: PointerElemAST): T

    /*
     * Visit ident and literal ASTs.
     */
    fun visitIdentAST(ast: IdentAST): T

    fun visitIntLiterAST(ast: IntLiterAST): T
    fun visitBoolLiterAST(ast: BoolLiterAST): T
    fun visitStrLiterAST(ast: StrLiterAST): T
    fun visitCharLiterAST(ast: CharLiterAST): T
    fun visitNullPairLiterAST(ast: NullPairLiterAST): T
    fun visitArrayLiterAST(ast: ArrayLiterAST): T

    /*
     * Visit type ASTs.
     */
    fun visitTypeAST(ast: TypeAST): T

}