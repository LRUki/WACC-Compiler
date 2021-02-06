package wacc.frontend

import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import wacc.frontend.ast.*
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairChoice
import wacc.frontend.ast.pair.PairElemAST

class BuildAstVisitor : WaccParserBaseVisitor<AST>() {

    override fun visitProgram(ctx: WaccParser.ProgramContext): AST {
        var funcList = emptyList<FuncAST>()
        for (func in ctx.func()) { // Read all func, skip "begin", "end" and EOF
            funcList = funcList + visit(func) as FuncAST
        }

        var stat = visit(ctx.stat()) as StatAST

        return ProgramAST(funcList, statToList(stat))
    }

    override fun visitFunc(ctx: WaccParser.FuncContext): AST {
        var paramList = emptyList<ParamAST>()
        if (ctx.paramList() != null) {
            for (param in ctx.paramList().param()) {
                paramList += visit(param) as ParamAST
            }
        }
        return FuncAST(visit(ctx.type()) as TypeAST,
                visit(ctx.ident()) as IdentAST,
                paramList,
                statToList(visit(ctx.stat()) as StatAST)
        )
    }

    override fun visitParam(ctx: WaccParser.ParamContext): AST {
        return ParamAST(visit(ctx.type()) as TypeAST, visit(ctx.ident()) as IdentAST)
    }

    override fun visitReadStat(ctx: WaccParser.ReadStatContext): AST {
        return ReadStatAST(visit(ctx.assignLhs()) as LhsAST)
    }

    override fun visitIfStat(ctx: WaccParser.IfStatContext): AST {
        return IfStatAST(visit(ctx.expr()) as ExprAST,
                statToList(visit(ctx.stat(0)) as StatAST),
                statToList(visit(ctx.stat(1)) as StatAST))
    }

    override fun visitBlockStat(ctx: WaccParser.BlockStatContext): AST {
        return BlockStatAST(statToList(visit(ctx.stat()) as StatAST))
    }

    override fun visitMultiStat(ctx: WaccParser.MultiStatContext): AST {
        val stat1 = visit(ctx.stat(0)) as StatAST
        val stat2 = visit(ctx.stat(1)) as StatAST
        assert(stat2 !is MultiStatAST)
        return if (stat1 is MultiStatAST) {
            MultiStatAST(stat1.stats + stat2)
        } else {
            MultiStatAST(listOf(stat1, stat2))
        }
    }

    override fun visitSkipStat(ctx: WaccParser.SkipStatContext?): AST {
        return SkipStatAST()
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

    override fun visitAssignStat(ctx: WaccParser.AssignStatContext): AST {
        return AssignStatAST(visit(ctx.assignLhs()) as LhsAST,
                visit(ctx.assignRhs()) as RhsAST)
    }

    override fun visitDeclareStat(ctx: WaccParser.DeclareStatContext): AST {
        return DeclareStatAST(visit(ctx.type()) as TypeAST,
                visit(ctx.ident()) as IdentAST,
                visit(ctx.assignRhs()) as RhsAST)
    }

    override fun visitWhileStat(ctx: WaccParser.WhileStatContext): AST {
        return WhileStatAST(visit(ctx.expr()) as ExprAST,
                statToList(visit(ctx.stat()) as StatAST))
    }

    private fun statToList(stat: StatAST): List<StatAST> {
        return if (stat is MultiStatAST) {
            stat.stats
        } else {
            listOf(stat)
        }
    }

    override fun visitAssignLhs(ctx: WaccParser.AssignLhsContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitAssignRhs(ctx: WaccParser.AssignRhsContext): AST {
        return when {
            ctx.NEWPAIR() != null -> NewPairRhsAST(
                    visit(ctx.expr(0)) as ExprAST,
                    visit(ctx.expr(1)) as ExprAST
            )
            ctx.CALL() != null -> {
                var argList = emptyList<ExprAST>()
                if (ctx.argList() != null) {
                    for (expr in ctx.argList().expr()) {
                        argList += visit(expr) as ExprAST
                    }
                }
                CallRhsAST(visit(ctx.ident()) as IdentAST, argList)
            }
            else -> visitChildren(ctx)
        }
    }

    override fun visitPairElem(ctx: WaccParser.PairElemContext): AST {
        val choice = when {
            ctx.FST() != null -> PairChoice.FST
            ctx.SND() != null -> PairChoice.SND
            else -> throw RuntimeException()
        }
        return PairElemAST(choice, visit(ctx.expr()) as ExprAST)
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

    override fun visitArrayType(ctx: WaccParser.ArrayTypeContext): AST {
        val dimension = ctx.L_SQUARE().size
        val innerType = visit(ctx.getChild(0)) as TypeAST
        return ArrayTypeAST(innerType, dimension)
    }

    override fun visitPairType(ctx: WaccParser.PairTypeContext): AST {
        return PairTypeAST(visit(ctx.pairElemType(0)) as TypeAST, visit(ctx.pairElemType(1)) as TypeAST)
    }

    override fun visitPairElemType(ctx: WaccParser.PairElemTypeContext): AST {
        return if (ctx.PAIR() != null) {
            InnerPairTypeAST()
        } else {
            visitChildren(ctx)
        }
    }

    override fun visitUnopExpr(ctx: WaccParser.UnopExprContext): AST {
        val unopContext = ctx.unop()
        val unOp = when {
            unopContext.NOT() != null -> UnOp.NOT
            unopContext.MINUS() != null -> UnOp.MINUS
            unopContext.LEN() != null -> UnOp.LEN
            unopContext.ORD() != null -> UnOp.ORD
            unopContext.CHR() != null -> UnOp.CHR
            else -> throw RuntimeException()
        }
        return UnOpExprAST(unOp, visit(ctx.expr()) as ExprAST)
    }

    override fun visitSingletonExpr(ctx: WaccParser.SingletonExprContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitBinopExpr(ctx: WaccParser.BinopExprContext): AST {
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

    override fun visitParenExpr(ctx: WaccParser.ParenExprContext): AST {
        return visit(ctx.expr())
    }

    override fun visitArrayElem(ctx: WaccParser.ArrayElemContext): AST {
        var indices = emptyList<ExprAST>()
        for (expr in ctx.expr()) {
            indices += visit(expr) as ExprAST
        }
        return ArrayElemAST(visit(ctx.ident()) as IdentAST, indices)
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext): AST {
        return IntLiterAST(Integer.parseInt(ctx.text))
    }

    override fun visitBoolLiter(ctx: WaccParser.BoolLiterContext): AST {
        return when {
            ctx.TRUE() != null -> BoolLiterAST(true)
            ctx.FALSE() != null -> BoolLiterAST(false)
            else -> throw RuntimeException()
        }
    }

    override fun visitStrLiter(ctx: WaccParser.StrLiterContext): AST {
        return StrLiterAST(replaceEscapeChars(ctx.text))
    }

    override fun visitCharLiter(ctx: WaccParser.CharLiterContext): AST {
        return CharLiterAST(replaceEscapeChars(ctx.text)[0])
    }

    private fun replaceEscapeChars(str: String): String {
        val trimmedText = str.substring(1, str.length - 1)
        val output = trimmedText
                .replace("\\0", 0.toChar().toString())
                .replace("\\b", "\b")
                .replace("\\t", "\t")
                .replace("\\n", "\n")
                .replace("\\f", 12.toChar().toString())
                .replace("\\r", "\r")
                .replace("\\\"", "\"")
                .replace("\\\'", "\'")
                .replace("\\\\", "\\")
        return output
    }

    override fun visitArrayLiter(ctx: WaccParser.ArrayLiterContext): AST {
        var values = emptyList<ExprAST>()
        for (expr in ctx.expr()) {
            values += visit(expr) as ExprAST
        }
        return ArrayLiterAST(values)
    }

    override fun visitPairLiter(ctx: WaccParser.PairLiterContext?): AST {
        return NullPairLiterAST()
    }

    override fun visitIdent(ctx: WaccParser.IdentContext): AST {
        return IdentAST(ctx.text)
    }


}