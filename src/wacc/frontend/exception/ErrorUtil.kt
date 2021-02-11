package wacc.frontend.exception

import org.antlr.v4.runtime.ParserRuleContext

fun syntaxError(msg: String, ctx: ParserRuleContext) {
    throw SyntaxException(
            "Syntax Error at line" +
                    " ${ctx.start.line}:${ctx.start.charPositionInLine} $msg", ctx.start.line
    )
}

fun semanticError(msg: String, ctx: ParserRuleContext) {
    throw SemanticException(
            "Semantic Error at line ${ctx.start.line}:${ctx.start.charPositionInLine} $msg", ctx.start.line
    )
}



