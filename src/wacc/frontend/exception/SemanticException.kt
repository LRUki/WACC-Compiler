package wacc.frontend.exception

class SemanticException(message: String, val line: Int) : Exception(message) {
    val errorCode = 200
}