package wacc.frontend

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.ast.*
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.exception.SemanticException

class SemanticAnalyser {
    companion object {
        val defCharTypeAST = BaseTypeAST(BaseType.CHAR)
        val defIntTypeAST = BaseTypeAST(BaseType.INT)
        val defBoolTypeAST = BaseTypeAST(BaseType.BOOL)
        val defStringTypeAST = BaseTypeAST(BaseType.STRING)
        val defAnyTypeAST = BaseTypeAST(BaseType.ANY)
        val defArrayTypeAST = ArrayTypeAST(defAnyTypeAST, 1)

        fun semanticError(message: String, ctx: ParserRuleContext) {
            throw SemanticException("Semantic Error at line ${ctx.start.line}:${ctx.start.charPositionInLine} $message"
                    , ctx.start.line)
        }
    }
}


fun getTypeString(typeAST: TypeAST): String {
    return when (typeAST) {
        is BaseTypeAST -> typeAST.type.name.toLowerCase()
        is ArrayTypeAST -> getTypeString(typeAST.type) //handle case
        is PairTypeAST -> "pair"
        is InnerPairTypeAST -> "TODO" //TODO
        else -> throw RuntimeException()
    }
}

// Gets the type name of expr assuming it is well formed
fun getExprType(exprAST: ExprAST): String {
    val exp: ExprAST = when (exprAST) {
        is BinOpExprAST -> exprAST.expr1
        is UnOpExprAST -> exprAST.expr
        else -> throw RuntimeException()
    }
    return when (exp) {
        is IntLiterAST -> "int"
        is BoolLiterAST -> "bool"
        is StrLiterAST -> "string"
        //CHECK COMPLETE
        is CharLiterAST -> "char"

        is NullPairLiterAST -> "TODO" // TODO
        is ArrayElemAST -> "TODO" // TODO
        is IdentAST -> "TODO" // TODO

        else -> throw RuntimeException()
    }
}

fun getRhsType(rhsAST: RhsAST): String {
    return when (rhsAST) {
        is ExprAST -> getExprType(rhsAST)
        is ArrayLiterAST -> getExprType(rhsAST.values[0])
//        is NewPairRhsAST -> "pair" recurse
        is CallRhsAST -> "TODO" //TODO
        else -> throw RuntimeException()
    }
}

fun analyse() {
    print("TODO")
}
