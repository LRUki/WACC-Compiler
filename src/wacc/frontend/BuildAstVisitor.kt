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
        TODO()
        return visitChildren(ctx)
    }

    override fun visitParamList(ctx: WaccParser.ParamListContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitParam(ctx: WaccParser.ParamContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitReadStat(ctx: WaccParser.ReadStatContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitIfStat(ctx: WaccParser.IfStatContext): AST {
        return IfStatAST(visit(ctx.expr()) as ExprAST,
                visit(ctx.stat(0)) as StatAST,
                visit(ctx.stat(1)) as StatAST)
    }

    override fun visitBlockStat(ctx: WaccParser.BlockStatContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitMultiStat(ctx: WaccParser.MultiStatContext): AST {
        return MultiStatAST(visit(ctx.stat(0)) as StatAST,
                visit(ctx.stat(1)) as StatAST)
    }

    override fun visitSkipStat(ctx: WaccParser.SkipStatContext?): AST {
        TODO()
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
        TODO()
        return visitChildren(ctx)
    }

    override fun visitDeclareStat(ctx: WaccParser.DeclareStatContext): AST {
        return DeclareStatAST(visit(ctx.type()) as TypeAST,
                visit(ctx.ident()) as IdentAST,
                visit(ctx.assignRhs()) as RhsAST)
    }

    override fun visitWhileStat(ctx: WaccParser.WhileStatContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitAssignLhs(ctx: WaccParser.AssignLhsContext?): AST {
        TODO()
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
        TODO()
        return visitChildren(ctx)
    }

    override fun visitPairElem(ctx: WaccParser.PairElemContext?): AST {
        TODO()
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
        TODO()
        return visitChildren(ctx)
    }

    override fun visitPairType(ctx: WaccParser.PairTypeContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitPairElemType(ctx: WaccParser.PairElemTypeContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitUnopExpr(ctx: WaccParser.UnopExprContext): AST {
        val unopContext = ctx.unop()
        val unOp = when {
            unopContext.NOT() != null -> UnOp.NOT
            unopContext.MINUS() != null ->UnOp.MINUS
            unopContext.LEN() != null ->UnOp.LEN
            unopContext.ORD() != null ->UnOp.ORD
            unopContext.CHR() != null ->UnOp.CHR
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

    override fun visitArrayElem(ctx: WaccParser.ArrayElemContext?): AST {
        TODO()
        return visitChildren(ctx)
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

    override fun visitArrayLiter(ctx: WaccParser.ArrayLiterContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitPairLiter(ctx: WaccParser.PairLiterContext?): AST {
        TODO()
        return visitChildren(ctx)
    }

    override fun visitIdent(ctx: WaccParser.IdentContext): AST {
        return IdentAST(ctx.text)
    }


}