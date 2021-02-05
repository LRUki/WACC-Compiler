package wacc.frontend.identifiers

import wacc.frontend.ast.AST

open class Identifier(val type: Type, node: AST)

class Variable(type: Type, node: AST) : Identifier(type, node)
