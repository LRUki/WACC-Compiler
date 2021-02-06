package wacc.frontend.identifiers

import wacc.frontend.ast.AST

open class Identifier(val type: Type, var node: AST?=null) {
    open fun isArray(): Boolean {
        return false
    }
}

class Variable(type: Type) : Identifier(type)
