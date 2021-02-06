package wacc.frontend.exceptions

class AntlrException(antlrMsg: String) :
        SyntaxException("Parse Error $antlrMsg")
