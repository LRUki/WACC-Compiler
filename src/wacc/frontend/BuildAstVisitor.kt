package wacc.frontend

import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import antlr.WaccParserVisitor
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.RuleNode
import org.antlr.v4.runtime.tree.TerminalNode
import wacc.frontend.ast.AstNode

class BuildAstVisitor : WaccParserBaseVisitor<AstNode>() {

    override fun visitProgram(ctx: WaccParser.ProgramContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitFunc(ctx: WaccParser.FuncContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitParamList(ctx: WaccParser.ParamListContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitParam(ctx: WaccParser.ParamContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitReadStat(ctx: WaccParser.ReadStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitIfStat(ctx: WaccParser.IfStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBlockStat(ctx: WaccParser.BlockStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitMultiStat(ctx: WaccParser.MultiStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitSkipStat(ctx: WaccParser.SkipStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitActionStat(ctx: WaccParser.ActionStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitAssignStat(ctx: WaccParser.AssignStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitDeclareStat(ctx: WaccParser.DeclareStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitWhileStat(ctx: WaccParser.WhileStatContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitAssignLhs(ctx: WaccParser.AssignLhsContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitAssignRhs(ctx: WaccParser.AssignRhsContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitArgList(ctx: WaccParser.ArgListContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitPairElem(ctx: WaccParser.PairElemContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitType(ctx: WaccParser.TypeContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBaseType(ctx: WaccParser.BaseTypeContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitArrayType(ctx: WaccParser.ArrayTypeContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitPairType(ctx: WaccParser.PairTypeContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitPairElemType(ctx: WaccParser.PairElemTypeContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitExpr(ctx: WaccParser.ExprContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitUnaryOper(ctx: WaccParser.UnaryOperContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBinop1(ctx: WaccParser.Binop1Context?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBinop2(ctx: WaccParser.Binop2Context?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBinop3(ctx: WaccParser.Binop3Context?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBinop4(ctx: WaccParser.Binop4Context?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBinop5(ctx: WaccParser.Binop5Context?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBinop6(ctx: WaccParser.Binop6Context?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitArrayElem(ctx: WaccParser.ArrayElemContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitBoolLiter(ctx: WaccParser.BoolLiterContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitStrLiter(ctx: WaccParser.StrLiterContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitCharLiter(ctx: WaccParser.CharLiterContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitArrayLiter(ctx: WaccParser.ArrayLiterContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitPairLiter(ctx: WaccParser.PairLiterContext?): AstNode {
        return visitChildren(ctx)
    }

    override fun visitIdent(ctx: WaccParser.IdentContext?): AstNode {
        return visitChildren(ctx)
    }


}