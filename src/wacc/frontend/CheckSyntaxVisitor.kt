package wacc.frontend
import antlr.WaccLexer
import antlr.WaccParser
import antlr.WaccParserBaseVisitor
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import java.io.File
import java.lang.Exception
import java.lang.System.exit
import java.io.PrintStream

fun main(){
    //overrides the printstream to list for stand error and throw exception
    class CustomPrintStream(out: PrintStream?) : PrintStream(out) {
        override fun print(s: String) {
            throw Exception("Syntax failure!")
        }
    }

    System.setErr(CustomPrintStream(System.err));
    val folder = File("wacc_examples/invalid/syntaxErr")
    val list = actionOnFiles(folder) { file ->
        val input = CharStreams.fromStream(file.inputStream())
        val lexer = WaccLexer(input)
        try{
            val tokens = CommonTokenStream(lexer)
            val parser = WaccParser(tokens)
            val tree = parser.program()
            val syntaxVisitor = SyntaxVisitor()
            syntaxVisitor.visit(tree)
            System.out.println("shouldn't reach here!" + file.path)
        }catch(e:Exception){
            println(file.path + " syntax failure as expected!")
        }
    }
}
class SyntaxVisitor : WaccParserBaseVisitor<Void>() {
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