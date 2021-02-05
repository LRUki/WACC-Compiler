package wacc.frontend

import antlr.WaccLexer
import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.lang.System.exit
import kotlin.system.exitProcess

fun main(){
//    val input = CharStreams.fromStream(File("wacc_examples/invalid/syntaxErr/function/mutualRecursionNoReturn.wacc").inputStream())
//    val input = CharStreams.fromStream(File("wacc_examples/invalid/syntaxErr/function/functionJunkAfterReturn.wacc").inputStream())
//    val input = CharStreams.fromStream(File("wacc_examples/invalid/syntaxErr/function/functionConditionalNoReturn.wacc").inputStream())
//    val input = CharStreams.fromStream(File("wacc_examples/invalid/syntaxErr/function/functionReturnInLoop.wacc").inputStream())
//    val input = CharStreams.fromStream(File("wacc_examples/invalid/syntaxErr/function/functionNoReturn.wacc").inputStream())
    val input = CharStreams.fromStream(File("wacc_examples/invalid/syntaxErr/variables/bigIntAssignment.wacc").inputStream())

//    val input = CharStreams.fromStream(File("wacc_examples/valid/function/nested_functions/functionConditionalReturn.wacc").inputStream())
    val lexer = WaccLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = WaccParser(tokens)
    val tree = parser.program()
    val visitor = SyntaxVisitor()
    visitor.visit(tree)
}
class SyntaxVisitor : WaccParserBaseVisitor<Void>() {

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
            syntaxError()
        }
        return null
    }

    override fun visitIntLiter(ctx: WaccParser.IntLiterContext): Void? {
        try {
            (ctx.text).toInt()
        } catch (e: NumberFormatException) {
            syntaxError()
        }
        return null
    }

    private fun syntaxError() {
        System.err.println("#syntax error#")
        exit(100)
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