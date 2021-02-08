package wacc.frontend
import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.exception.SyntaxErrorException

class CheckSyntaxVisitor : WaccParserBaseVisitor<Void>() {
    override fun visitFunc(ctx: WaccParser.FuncContext): Void? {
        //check if function ends with return or exit
        val lastStat: WaccParser.StatContext = getLastStat(ctx.stat())
        var functionEndsWithExitOrReturn = isExitOrReturn(lastStat)

        //if the last statement is ifStat, check both branch ends with return/exit
        if (lastStat is WaccParser.IfStatContext){
            functionEndsWithExitOrReturn =
                ifStatEndsWithExitOrReturn(lastStat)
        }

        if(!functionEndsWithExitOrReturn){
            syntaxError(ctx,"function missing exit or return")
        }

        return null
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext): Void? {
        try {
            (ctx.text).toInt()
        } catch (e: NumberFormatException) {
            syntaxError(ctx, "int out of bound")
        }
        return null
    }

    private fun syntaxError(ctx: ParserRuleContext, message:String){
        throw SyntaxErrorException("syntax error: " +
            "line: " + ctx.start.line.toString() + ":" + ctx.start.charPositionInLine
                .toString() + " " + message)
    }


    //recursively search for the last statement
    private fun getLastStat(stat: WaccParser.StatContext): WaccParser.StatContext {
        var statList = emptyList<WaccParser.StatContext>()

        if(stat is WaccParser.MultiStatContext){
            statList = stat.stat()
        }

        if(statList.isNotEmpty()){
            return getLastStat(statList[statList.size - 1])
        }

        return stat
    }

    //check if statement ends with return/exit
    private fun ifStatEndsWithExitOrReturn(stat: WaccParser.IfStatContext) : Boolean {
        //check if branch ends with return/exit
        val ifLastStat = getLastStat(stat.stat(0))
        var endsWithExitOrReturn = isExitOrReturn(ifLastStat)
        if(!endsWithExitOrReturn && ifLastStat is WaccParser.IfStatContext){
            endsWithExitOrReturn = ifStatEndsWithExitOrReturn(ifLastStat)
        }

        //check else branch ends with return/exit
        val elseLastStat = getLastStat(stat.stat(1))
        if(endsWithExitOrReturn
            && elseLastStat is WaccParser.IfStatContext){
            endsWithExitOrReturn = ifStatEndsWithExitOrReturn(elseLastStat)
        }

        return endsWithExitOrReturn
    }

    //checks if the given statement is EXIT or RETURN
    private fun isExitOrReturn(statCtx: WaccParser.StatContext):Boolean {
        if(statCtx is WaccParser.ActionStatContext &&
            (statCtx.EXIT() != null || statCtx.RETURN() != null)){
            return true
        }
        return false
    }
}