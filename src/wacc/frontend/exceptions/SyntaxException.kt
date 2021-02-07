package wacc.frontend.exceptions

open class SyntaxException(var msg:String) : Exception(msg) {

    class IntegerSyntaxException(string: String) :
            SyntaxException("$string is not a valid integer")

    class UnknownUnaryOperationException(unOp: String) :
            SyntaxException("Unknown unary operator \"$unOp\"")

    class UnknownBinaryOperationException(binOp: String) :
            SyntaxException("Unknown unary operator \"$binOp\"")

    class UnknownExprTypeException :
            SyntaxException("Unknown type of expression")

    class EmptyMainProgramException :
            SyntaxException("Program body is empty")

    class LastStatementIsNotTerminatorException :
            SyntaxException("The last statement should be a return, exit, " +
                    "\n  or an if-statement with both branches ending with a terminator")

    class UnsupportedArrayBaseTypeException(type: String) :
            SyntaxException("\"$type\" is not a valid base type for arrays")
}