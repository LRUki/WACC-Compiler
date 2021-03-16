package wacc.frontend.visitor

import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import wacc.frontend.ast.AST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.LiterAST
import wacc.frontend.exception.SyntaxException
import wacc.frontend.exception.syntaxError

/**
 * Visitor class that handles syntax checking.
 * checks the syntax of function and integer literal
 * that antlr fails to check.
 *
 * @throws SyntaxException
 */
class CheckSyntaxVisitor : WaccParserBaseVisitor<Void>() {
    override fun visitFunc(ctx: WaccParser.FuncContext): Void? {
        //check if function ends with return or exit
        val lastStat: WaccParser.StatContext = getLastStat(ctx.stat())
        var functionEndsWithExitOrReturn = isExitOrReturn(lastStat)

        //if the last statement is ifStat, check both branch ends with return/exit
        if (lastStat is WaccParser.IfStatContext) {
            functionEndsWithExitOrReturn =
                    ifStatEndsWithExitOrReturn(lastStat)
        }

        if (!functionEndsWithExitOrReturn) {
            syntaxError("Function does not end with an exit or return statement", ctx)
        }

        return null
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext): Void? {
        try {
            (ctx.text).toInt()
        } catch (e: NumberFormatException) {
            syntaxError("int out of bound", ctx)
        }
        return null
    }


    //recursively search for the last statement
    private fun getLastStat(stat: WaccParser.StatContext): WaccParser.StatContext {
        var statList = emptyList<WaccParser.StatContext>()

        if (stat is WaccParser.MultiStatContext) {
            statList = stat.stat()
        }

        if (statList.isNotEmpty()) {
            return getLastStat(statList[statList.size - 1])
        }

        return stat
    }

    //check if statement ends with return/exit
    private fun ifStatEndsWithExitOrReturn(stat: WaccParser.IfStatContext): Boolean {
        //check if branch ends with return/exit
        val ifLastStat = getLastStat(stat.stat(0))
        var endsWithExitOrReturn = isExitOrReturn(ifLastStat)
        if (!endsWithExitOrReturn && ifLastStat is WaccParser.IfStatContext) {
            endsWithExitOrReturn = ifStatEndsWithExitOrReturn(ifLastStat)
        }

        //check else branch ends with return/exit
        val elseLastStat = getLastStat(stat.stat(1))
        if (endsWithExitOrReturn) {
            if (elseLastStat is WaccParser.IfStatContext) {
                endsWithExitOrReturn = ifStatEndsWithExitOrReturn(elseLastStat)
            } else {
                endsWithExitOrReturn = isExitOrReturn(elseLastStat)
            }
        }

        return endsWithExitOrReturn
    }

    //checks if the given statement is EXIT or RETURN
    private fun isExitOrReturn(statCtx: WaccParser.StatContext): Boolean {
        if (statCtx is WaccParser.ActionStatContext &&
                (statCtx.EXIT() != null || statCtx.RETURN() != null)) {
            return true
        }
        return false
    }

    override fun visitUnopExpr(ctx: WaccParser.UnopExprContext): Void? {
        if (ctx.unop().MULT() != null) {
            val expr = BuildAstVisitor().visit(ctx.expr()) as ExprAST
            if (expr is LiterAST) {
                // Throw a syntax error instead of a semantic error here to make
                // invalid/syntaxErr/expressions/missingOperand1.wacc exit with correct code.
                syntaxError("Dereference operator cannot apply to literals", ctx)
            }
        }
        return null
    }
}