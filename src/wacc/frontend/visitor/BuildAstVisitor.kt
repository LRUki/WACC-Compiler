package wacc.frontend.visitor

import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.ast.AST
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.*
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.pair.PairChoice
import wacc.frontend.ast.pair.PairElemAST
import wacc.frontend.ast.pointer.PointerElemAST
import wacc.frontend.ast.program.ImportAST
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.MultiStatAST
import wacc.frontend.ast.statement.SkipStatAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.statement.block.BlockStatAST
import wacc.frontend.ast.statement.block.ForStatAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.*
import wacc.frontend.ast.struct.StructAccessAST
import wacc.frontend.ast.type.*

class BuildAstVisitor : WaccParserBaseVisitor<AST>() {

    override fun visitProgram(ctx: WaccParser.ProgramContext): AST {
        val funcList = mutableListOf<FuncAST>()
        for (func in ctx.func()) { // Read all func, skip "begin", "end" and EOF
            funcList += visit(func) as FuncAST
        }

        val stat = visit(ctx.stat()) as StatAST

        val importList = mutableListOf<ImportAST>()
        for (import in ctx.importStat()) {
            importList += visit(import) as ImportAST
        }

        val programAST = ProgramAST(importList, statToList(stat), funcList)
        programAST.ctx = ctx
        return programAST
    }


    override fun visitFunc(ctx: WaccParser.FuncContext): AST {
        val paramList = emptyList<ParamAST>().toMutableList()
        if (ctx.paramList() != null) {
            for (param in ctx.paramList().param()) {
                paramList += visit(param) as ParamAST
            }
        }

        val returnType = if (ctx.VOID() == null) {
            visit(ctx.type()) as TypeAST
        } else {
            VoidTypeAST()
        }
        val funcAST = FuncAST(returnType,
                visit(ctx.ident()) as IdentAST,
                paramList,
                statToList(visit(ctx.stat()) as StatAST)
        )
        funcAST.ctx = ctx
        return funcAST
    }

    override fun visitParam(ctx: WaccParser.ParamContext): AST {
        return ParamAST(visit(ctx.type()) as TypeAST, visit(ctx.ident()) as IdentAST)
    }

    override fun visitImportStat(ctx: WaccParser.ImportStatContext): AST {
        val filename = visit(ctx.ident(0)) as IdentAST
        val extension = visit(ctx.ident(1)) as IdentAST
        val importAst = ImportAST(IdentAST("${filename.name}.${extension.name}"))
        importAst.ctx = ctx
        return importAst
    }

    override fun visitReadStat(ctx: WaccParser.ReadStatContext): AST {
        val readStatAST = ReadStatAST(visit(ctx.assignLhs()) as LhsAST)
        readStatAST.ctx = ctx
        return readStatAST
    }

    override fun visitIfStat(ctx: WaccParser.IfStatContext): AST {
        val ifStatAST = IfStatAST(visit(ctx.expr()) as ExprAST,
                statToList(visit(ctx.stat(0)) as StatAST),
                statToList(visit(ctx.stat(1)) as StatAST))
        ifStatAST.ctx = ctx
        return ifStatAST
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
        val actionStatAST = ActionStatAST(action, visit(ctx.expr()) as ExprAST)
        actionStatAST.ctx = ctx
        return actionStatAST
    }

    override fun visitVoidReturnStat(ctx: WaccParser.VoidReturnStatContext?): AST {
        return VoidReturnStatAST()
    }

    override fun visitCallStat(ctx: WaccParser.CallStatContext): AST {
        // Build the CallRhsAST in the same way as in visitAssignRhs()
        val argList = mutableListOf<ExprAST>()
        if (ctx.argList() != null) {
            for (expr in ctx.argList().expr()) {
                argList += visit(expr) as ExprAST
            }
        }
        val callRhsAST = CallRhsAST(visit(ctx.ident()) as IdentAST, argList)
        callRhsAST.ctx = ctx

        // Build the CallStatAST from the CallRhsAST built
        val callStatAST = CallStatAST(callRhsAST)
        callStatAST.ctx = ctx
        return callStatAST
    }

    override fun visitAssignStat(ctx: WaccParser.AssignStatContext): AST {
        val assignStatAST = AssignStatAST(visit(ctx.assignLhs()) as LhsAST,
                visit(ctx.assignRhs()) as RhsAST)
        assignStatAST.ctx = ctx
        return assignStatAST
    }

    override fun visitDeclareStat(ctx: WaccParser.DeclareStatContext): AST {
        val declareStatAST = when {
            ctx.type() != null -> DeclareStatAST(visit(ctx.type()) as TypeAST,
                    visit(ctx.ident()) as IdentAST,
                    visit(ctx.assignRhs()) as RhsAST)
            ctx.implicitType() != null -> DeclareStatAST(visit(ctx.implicitType()) as ImplicitTypeAST,
                    visit(ctx.ident()) as IdentAST,
                    visit(ctx.assignRhs()) as RhsAST)
            else -> throw RuntimeException()
        }

        declareStatAST.ctx = ctx
        return declareStatAST
    }

    override fun visitStructDeclareStat(ctx: WaccParser.StructDeclareStatContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitStructDeclare(ctx: WaccParser.StructDeclareContext): AST {
        val fieldList = mutableListOf<StructFieldAST>()
        for (field in ctx.structMember()) {
            fieldList += StructFieldAST(visit(field.type()) as TypeAST,
                    visit(field.ident()) as IdentAST)
        }
        val structDeclare = StructDeclareStatAST(visit(ctx.structType().capitalisedIdent()) as IdentAST, fieldList)
        structDeclare.ctx = ctx
        return structDeclare
    }

    override fun visitStructAssign(ctx: WaccParser.StructAssignContext): AST {
        val assignments = mutableListOf<RhsAST>()
        for (rhs in ctx.assignRhs()) {
            assignments += visit(rhs) as RhsAST
        }
        val structAssign = StructAssignRhsAST(assignments)
        structAssign.ctx = ctx
        return structAssign
    }

    override fun visitWhileStat(ctx: WaccParser.WhileStatContext): AST {
        val whileStatAst = WhileStatAST(visit(ctx.expr()) as ExprAST,
                statToList(visit(ctx.stat()) as StatAST))
        whileStatAst.ctx = ctx
        return whileStatAst
    }

    override fun visitForStat(ctx: WaccParser.ForStatContext): AST {
        val forStatAst = ForStatAST(visit(ctx.stat(0)) as StatAST,visit(ctx.expr()) as ExprAST,
                visit(ctx.stat(1)) as StatAST, statToList(visit(ctx.stat(2)) as StatAST))
        forStatAst.ctx = ctx
        return forStatAst
    }

    private fun statToList(stat: StatAST): List<StatAST> {
        return if (stat is MultiStatAST) {
            stat.stats
        } else {
            listOf(stat)
        }
    }

    override fun visitStructType(ctx: WaccParser.StructTypeContext): AST {
        return StructTypeAST(visit(ctx.capitalisedIdent()) as IdentAST)
    }

    override fun visitAssignLhs(ctx: WaccParser.AssignLhsContext): AST {
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
                val callRhsAST = CallRhsAST(visit(ctx.ident()) as IdentAST, argList)
                callRhsAST.ctx = ctx
                callRhsAST
            }
            ctx.structAssign() != null -> {
                visit(ctx.structAssign()) as StructAssignRhsAST
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
        val pariElemAST = PairElemAST(choice, visit(ctx.expr()) as ExprAST)
        pariElemAST.ctx = ctx
        return pariElemAST
    }

    override fun visitPointerElem(ctx: WaccParser.PointerElemContext): AST {
        val pointerElemAST = PointerElemAST(visit(ctx.ident()) as IdentAST)
        pointerElemAST.ctx = ctx
        return pointerElemAST
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
        val innerType = if (ctx.pointerType() == null) {
            visit(ctx.getChild(0)) as TypeAST
        } else {
            visit(ctx.pointerType()) as PointerTypeAST
        }
        return ArrayTypeAST(innerType, dimension)
    }

    override fun visitPairType(ctx: WaccParser.PairTypeContext): AST {
        return PairTypeAST(visit(ctx.pairElemType(0)) as TypeAST, visit(ctx.pairElemType(1)) as TypeAST)
    }

    override fun visitPairElemType(ctx: WaccParser.PairElemTypeContext): AST {
        return if (ctx.PAIR() != null) {
            AnyPairTypeAST()
        } else {
            visitChildren(ctx)
        }
    }

    override fun visitPointerType(ctx: WaccParser.PointerTypeContext): AST {
        val depth = ctx.MULT().size
        val innerType = visit(ctx.getChild(0)) as TypeAST
        var output = innerType
        for (i in 1..depth) {
            output = PointerTypeAST(output)
        }
        return output
    }

    override fun visitImplicitType(ctx: WaccParser.ImplicitTypeContext): AST {
        return ImplicitTypeAST()
    }

    override fun visitUnopExpr(ctx: WaccParser.UnopExprContext): AST {
        val unopContext = ctx.unop()
        val unOp = when {
            unopContext.NOT() != null -> UnOp.NOT
            unopContext.MINUS() != null -> UnOp.MINUS
            unopContext.LEN() != null -> UnOp.LEN
            unopContext.ORD() != null -> UnOp.ORD
            unopContext.CHR() != null -> UnOp.CHR
            unopContext.REF() != null -> UnOp.REF
            unopContext.MULT() != null -> UnOp.DEREF
            else -> throw RuntimeException()
        }

        val unOpExprAST = UnOpExprAST(unOp, visit(ctx.expr()) as ExprAST)
        unOpExprAST.ctx = ctx
        return unOpExprAST
    }

    override fun visitSingletonExpr(ctx: WaccParser.SingletonExprContext?): AST {
        return visitChildren(ctx)
    }

    override fun visitStructAccess(ctx: WaccParser.StructAccessContext): AST {
        val structAccessAST = StructAccessAST(visit(ctx.ident(0)) as IdentAST,
                visit(ctx.ident(1)) as IdentAST)
        structAccessAST.ctx = ctx
        return structAccessAST
    }

    override fun visitStructAccessExpr(ctx: WaccParser.StructAccessExprContext): AST {
        return visitChildren(ctx)
    }

    override fun visitBinopExpr(ctx: WaccParser.BinopExprContext): AST {
        val binop = when (ctx.getChild(1).text) {
            "+" -> IntBinOp.PLUS
            "-" -> IntBinOp.MINUS
            "*" -> IntBinOp.MULT
            "/" -> IntBinOp.DIV
            "%" -> IntBinOp.MOD
            ">=" -> CmpBinOp.GTE
            ">" -> CmpBinOp.GT
            "<=" -> CmpBinOp.LTE
            "<" -> CmpBinOp.LT
            "==" -> CmpBinOp.EQ
            "!=" -> CmpBinOp.NEQ
            "&&" -> BoolBinOp.AND
            "||" -> BoolBinOp.OR
            else -> throw RuntimeException()
        }
        val binOpExprAST = BinOpExprAST(binop as BinOp,
                visit(ctx.expr(0)) as ExprAST,
                visit(ctx.expr(1)) as ExprAST)
        binOpExprAST.ctx = ctx
        return binOpExprAST
    }

    override fun visitParenExpr(ctx: WaccParser.ParenExprContext): AST {
        return visit(ctx.expr())
    }

    override fun visitArrayElem(ctx: WaccParser.ArrayElemContext): AST {
        var indices = emptyList<ExprAST>()
        for (expr in ctx.expr()) {
            indices += visit(expr) as ExprAST
        }
        val arrayElemAST = ArrayElemAST(visit(ctx.ident()) as IdentAST, indices)
        arrayElemAST.ctx = ctx
        return arrayElemAST
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
        return StrLiterAST(trimQuote(ctx.text))
    }

    override fun visitCharLiter(ctx: WaccParser.CharLiterContext): AST {
        return CharLiterAST(replaceEscapeChars(trimQuote(ctx.text))[0])
    }

    private fun trimQuote(str: String): String {
        return str.substring(1, str.length - 1)
    }

    private fun replaceEscapeChars(str: String): String {
        return str
                .replace("\\0", 0.toChar().toString())
                .replace("\\b", "\b")
                .replace("\\t", "\t")
                .replace("\\n", "\n")
                .replace("\\f", 12.toChar().toString())
                .replace("\\r", "\r")
                .replace("\\\"", "\"")
                .replace("\\\'", "\'")
                .replace("\\\\", "\\")
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

    private fun generateIdentAST(ctx: ParserRuleContext): IdentAST {
        val identAST = IdentAST(ctx.text)
        identAST.ctx = ctx
        return identAST
    }

    override fun visitIdent(ctx: WaccParser.IdentContext): AST {
        return generateIdentAST(ctx)
    }

    override fun visitCapitalisedIdent(ctx: WaccParser.CapitalisedIdentContext): AST {
        return generateIdentAST(ctx)
    }

}