package wacc.frontend.exceptions

import java.lang.Exception

class SemanticError(val msg: String): Exception(msg) {
}