package wacc.frontend.exception

class SemanticException(message:String, val line:Int, val errorCode:Int): Exception(message)