package wacc.frontend

import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import wacc.frontend.ast.*

class BuildAstVisitor : WaccParserBaseVisitor<AST>() {

    override fun visitProgram(ctx: WaccParser.ProgramContext): AST {
        var funcList = emptyList<FuncAST>()
        for (func in ctx.func()) { // Read all func, skip "begin", "end" and EOF
            funcList = funcList + visit(func) as FuncAST
        }

        var stat = visit(ctx.stat()) as StatAST

        return ProgramAST(funcList, flatten(stat))
    }

    private fun flatten(stat: StatAST): List<StatAST> {
        return if (stat is MultiStatAST) {
            flatten(stat.stat1) + flatten(stat.stat2)
        } else {
            listOf(stat)
        }
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

    override fun visitIfStat(ctx: WaccParser.IfStatContext): AST {
        return IfStatAST(visit(ctx.expr()) as ExprAST,
                visit(ctx.stat(0)) as StatAST,
                visit(ctx.stat(1)) as StatAST)
    }

    override fun visitBlockStat(ctx: WaccParser.BlockStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitMultiStat(ctx: WaccParser.MultiStatContext): AST {
        return MultiStatAST(visit(ctx.stat(0)) as StatAST,
                visit(ctx.stat(1)) as StatAST)
    }

    override fun visitSkipStat(ctx: WaccParser.SkipStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitActionStat(ctx: WaccParser.ActionStatContext): AST {
        val action = when {
            ctx.FREE() != null -> Action.FREE
            ctx.RETURN() != null -> Action.RETURN
            ctx.EXIT() != null -> Action.EXIT
            ctx.PRINT() != null -> Action.PRINT
            ctx.PRINTLN() != null -> Action.PRINTLN
            else -> throw RuntimeException()
        }
        return ActionStatAST(action, visit(ctx.expr()) as ExprAST)
    }

    override fun visitAssignStat(ctx: WaccParser.AssignStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitDeclareStat(ctx: WaccParser.DeclareStatContext): AST {
        return DeclareStatAST(visit(ctx.type()) as TypeAST,
                visit(ctx.ident()) as IdentAST,
                visit(ctx.assignRhs()) as RhsAST)
    }

    override fun visitWhileStat(ctx: WaccParser.WhileStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitAssignLhs(ctx: WaccParser.AssignLhsContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitAssignRhs(ctx: WaccParser.AssignRhsContext): AST {
        return when {
            ctx.NEWPAIR() != null -> TODO("newpair")
            ctx.CALL() != null -> TODO("call")
            else -> visitChildren(ctx)
        }
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
        val baseType = when {
            ctx.INT() != null -> BaseType.INT
            ctx.BOOL() != null -> BaseType.BOOL
            ctx.CHAR() != null -> BaseType.CHAR
            ctx.STRING() != null -> BaseType.STRING
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

    override fun visitUnopExpr(ctx: WaccParser.UnopExprContext?): AST? {
        return visitChildren(ctx)
    }

    override fun visitSingletonExpr(ctx: WaccParser.SingletonExprContext?): AST? {
        return visitChildren(ctx)
    }

    override fun visitBinopExpr(ctx: WaccParser.BinopExprContext): AST? {
        val binop = when (ctx.getChild(1).text) {
            "+" -> BinOp.PLUS
            "-" -> BinOp.MINUS;
            "*" -> BinOp.MULT
            "/" -> BinOp.DIV
            "%" -> BinOp.MOD
            ">=" -> BinOp.GTE
            ">" -> BinOp.GT
            "<=" -> BinOp.LTE
            "<" -> BinOp.LT
            "==" -> BinOp.EQ
            "!=" -> BinOp.NEQ
            "&&" -> BinOp.AND
            "||" -> BinOp.OR
            else -> throw RuntimeException()
        }
        return BinOpExprAST(binop,
                visit(ctx.expr(0)) as ExprAST,
                visit(ctx.expr(1)) as ExprAST)
    }

    override fun visitParenExpr(ctx: WaccParser.ParenExprContext): AST? {
        return visit(ctx.expr())
    }

    override fun visitUnaryOper(ctx: WaccParser.UnaryOperContext?): AST {
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

    override fun visitStrLiter(ctx: WaccParser.StrLiterContext): AST {
        val text = ctx.text
        val trimmedText = text.substring(1, text.length - 1)
        return return StrLiterAST(trimmedText)
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