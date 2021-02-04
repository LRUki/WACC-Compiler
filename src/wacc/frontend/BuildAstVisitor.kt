package wacc.frontend

import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.ast.*

class BuildAstVisitor : WaccParserBaseVisitor<AST>() {

    override fun visitProgram(ctx: WaccParser.ProgramContext): AST {
        var funcList = emptyList<FuncAST>()
        for (i in 1..ctx.childCount - 4) { // Read all func
            funcList = funcList + ctx.getChild(i).accept(this) as FuncAST
        }

        return ProgramAST(funcList, ctx.getChild(ctx.childCount - 3).accept(this) as StatAST)
    }

    override fun visitFunc(ctx: WaccParser.FuncContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitParamList(ctx: WaccParser.ParamListContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitParam(ctx: WaccParser.ParamContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitReadStat(ctx: WaccParser.ReadStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitIfStat(ctx: WaccParser.IfStatContext?): AST {
        return IfStatAST()
    }

    override fun visitBlockStat(ctx: WaccParser.BlockStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitMultiStat(ctx: WaccParser.MultiStatContext): AST {
        return MultiStatAST(ctx.getChild(0).accept(this) as StatAST,
                ctx.getChild(2).accept(this) as StatAST)
    }

    override fun visitSkipStat(ctx: WaccParser.SkipStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitActionStat(ctx: WaccParser.ActionStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitAssignStat(ctx: WaccParser.AssignStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitDeclareStat(ctx: WaccParser.DeclareStatContext): AST {
        return DeclareStatAST(ctx.getChild(0).accept(this) as TypeAST,
                ctx.getChild(1).accept(this) as IdentAST,
                ctx.getChild(3).accept(this) as RhsAST)
    }

    override fun visitWhileStat(ctx: WaccParser.WhileStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitAssignLhs(ctx: WaccParser.AssignLhsContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitAssignRhs(ctx: WaccParser.AssignRhsContext): AST {
        if (ctx.getChild(0).text == "newpair") {

        } else if  (ctx.getChild(0).text == "newpair") {

        } else {
            return visitChildren(ctx)
        }

        return visitChildren(ctx)
    }

    override fun visitArgList(ctx: WaccParser.ArgListContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitPairElem(ctx: WaccParser.PairElemContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitType(ctx: WaccParser.TypeContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitBaseType(ctx: WaccParser.BaseTypeContext): AST {
        val baseType = when(ctx.text) {
            "int" -> BaseType.INT
            "bool" -> BaseType.BOOL
            "char" -> BaseType.CHAR
            "string" -> BaseType.STRING
            else -> throw RuntimeException()
        }
        return BaseTypeAST(baseType)
    }

    override fun visitArrayType(ctx: WaccParser.ArrayTypeContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitPairType(ctx: WaccParser.PairTypeContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitPairElemType(ctx: WaccParser.PairElemTypeContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitExpr(ctx: WaccParser.ExprContext): AST {
        if (ctx.childCount == 3) {
            if (ctx.getChild(0).text == "(") {
                return ctx.getChild(1).accept(this)
            } else {
                val binop = when(ctx.getChild(1).text) {
                    "+" -> BinOp.PLUS
                    "*" -> BinOp.MULT
                    else -> throw RuntimeException()
                }
                return BinOpExprAST(binop,
                        ctx.getChild(0).accept(this) as ExprAST,
                        ctx.getChild(2).accept(this) as ExprAST)
            }
        } else {
            return visitChildren(ctx)
        }
    }

    override fun visitUnaryOper(ctx: WaccParser.UnaryOperContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitBinop1(ctx: WaccParser.Binop1Context): AST {
        return visitChildren(ctx)
    }

    override fun visitBinop2(ctx: WaccParser.Binop2Context): AST {
        return visitChildren(ctx)
    }

    override fun visitBinop3(ctx: WaccParser.Binop3Context): AST {
        return visitChildren(ctx)
    }

    override fun visitBinop4(ctx: WaccParser.Binop4Context): AST {
        return visitChildren(ctx)
    }

    override fun visitBinop5(ctx: WaccParser.Binop5Context): AST {
        return visitChildren(ctx)
    }

    override fun visitBinop6(ctx: WaccParser.Binop6Context): AST {
        return visitChildren(ctx)
    }

    override fun visitArrayElem(ctx: WaccParser.ArrayElemContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext): AST {
        return IntLiterAST(Integer.parseInt(ctx.text))
    }

    override fun visitBoolLiter(ctx: WaccParser.BoolLiterContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitStrLiter(ctx: WaccParser.StrLiterContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitCharLiter(ctx: WaccParser.CharLiterContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitArrayLiter(ctx: WaccParser.ArrayLiterContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitPairLiter(ctx: WaccParser.PairLiterContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitIdent(ctx: WaccParser.IdentContext): AST {
        return IdentAST(ctx.text)
    }


}