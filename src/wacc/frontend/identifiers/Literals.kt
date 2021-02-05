package wacc.frontend.identifiers

class BoolLit(val value: Boolean) : BOOL()
class IntLit(val value: Int) : INT()
class CharLit(val value: Char) : CHAR()
class StringLit(val value: STRING) : STRING()

class PairLit : PAIR(Identifier(Type.NULL), Identifier(Type.NULL))
class Param(val ident: Identifier) : Identifier(ident.type)
class ArrayLit(val arrayElems: MutableList<Identifier>,
               val elemType: Type, val dimension: Int) : ARRAY(elemType, dimension)