package wacc.frontend.exception

class SyntaxException(message: String, val line: Int) : Exception(message) {
    val errorCode = 100
}