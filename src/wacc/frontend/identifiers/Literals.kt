package wacc.frontend.identifiers

class BoolLit(private val value: Boolean) : BOOL() {
    fun getValue(): Boolean {
        return value
    }
}

class IntLit(private val value: Int) : INT() {
    fun getValue(): Int{
        return value
    }
}

class CharLit(private val value: Char) : CHAR() {
    fun getValue(): Char {
        return value
    }
}

class StringLit(private val value: String) {
    fun getValue(): String {
        return value
    }
}

class PairLit : PAIR(Identifier(Type.NONE), Identifier(Type.NONE))

class Param(private val ident: Identifier) : Identifier(ident.getType()) {
    fun getIdent(): Identifier {
        return ident
    }
}

class ArrayLit(private val arrayElems: MutableList<Identifier>,
               private var elemType: Type) : ARRAY(elemType) {
    fun getArrayElems(): List<Identifier> {
        return arrayElems
    }

    fun getElemType(): Type {
        return elemType
    }

    fun setElemType(newElemType: Type) {
        elemType = newElemType
    }
}