package wacc.frontend.identifiers

import wacc.frontend.ast.AST

enum class Type(){
    INT,
    BOOL,
    CHAR,
    STRING,
    PAIR,
    ARRAY,
    NULL
}

open class INT() : Identifier(Type.INT)
open class BOOL() : Identifier(Type.BOOL)
open class CHAR() : Identifier(Type.CHAR)
open class STRING() : Identifier(Type.STRING)


open class PAIR() : Identifier(Type.PAIR)
open class ARRAY(arrayType: Type, val dimension: Int) : Identifier(arrayType) {
    override fun isArray() : Boolean {
        return true
    }
}
class Param(val ident: Identifier, node: AST) : Identifier(ident.type, node)