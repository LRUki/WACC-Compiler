package wacc.frontend.exception

class SyntaxException(message:String, val line:Int, val errorCode:Int): Exception(message)