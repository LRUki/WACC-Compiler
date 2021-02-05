package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.lang.System.exit
import kotlin.system.exitProcess

class CheckSyntaxVisitor : WaccParserBaseVisitor<Void>() {

    override fun visitFunc(ctx: WaccParser.FuncContext): Void? {
        //check if function ends with return or exit
        val lastStat: WaccParser.StatContext = getLastStat(ctx.stat())
        var functionEndsWithExitOrReturn = isExitOrReturn(lastStat)
        if (lastStat is WaccParser.IfStatContext){
            val ifLastStat = getLastStat(lastStat.stat(0))
            val elseLastStat = getLastStat(lastStat.stat(1))
            functionEndsWithExitOrReturn = isExitOrReturn(ifLastStat)
                    && isExitOrReturn(elseLastStat)
        }
        if(!functionEndsWithExitOrReturn){
            syntaxError("Function missing exit or return")
        }
        return null
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext): Void? {
        try {
            (ctx.text).toInt()
        } catch (e: NumberFormatException) {
            syntaxError("Int out of bound")
        }
        return null
    }

    private fun syntaxError(message: String) {
        throw IllegalArgumentException(message)
    }

    //checks if the given statement is EXIT or RETURN
    private fun isExitOrReturn(statCtx: WaccParser.StatContext):Boolean {
        if(statCtx is WaccParser.ActionStatContext &&
            (statCtx.EXIT() != null || statCtx.RETURN() != null)){
            return true
        }
        return false
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
}