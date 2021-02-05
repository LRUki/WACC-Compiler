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

open class INT(node: AST) : Identifier(Type.INT, node)
open class BOOL(node: AST) : Identifier(Type.BOOL, node)
open class CHAR(node: AST) : Identifier(Type.CHAR, node)
open class STRING(node: AST) : Identifier(Type.STRING, node)


open class PAIR(node: AST) : Identifier(Type.PAIR, node)
open class ARRAY(private val arrayType: Type, dimension: Int, node: AST) : Identifier(Type.ARRAY, node)
class Param(val ident: Identifier, node: AST) : Identifier(ident.type, node)