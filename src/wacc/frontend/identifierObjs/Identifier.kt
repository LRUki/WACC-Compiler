package wacc.frontend.identifierObjs

/* Types should store something (...) */
enum class Type(){
    INT(),
    BOOL(),
    CHAR(),
    PAIR(),
    ARRAY(),
    NONE()

//    STRING(),
//    ARGS(),
//    FUNC(),
//    STAT(),
//    PROGRAM(-1),
//    NONE();
}

open class Identifier(private val type: Type) {

    open fun getType(): Type {
        return type
    }
    open fun getBaseType(): Identifier {
        return this
    }


}

open class INT : Identifier(Type.INT) {
    final override fun getBaseType(): Identifier {
        return this
    }
}
open class BOOL : Identifier(Type.BOOL) {
    final override fun getBaseType(): Identifier {
        return this
    }
}
open class CHAR : Identifier(Type.CHAR) {
    final override fun getBaseType(): Identifier {
        return this
    }
}
open class PAIR(private val first: Identifier,
                private val second: Identifier) : Identifier(Type.PAIR) {
    fun getFirst(): Identifier {
        return first
    }

    fun getSecond(): Identifier {
        return second
    }

    override fun getBaseType(): Identifier {
        return this
    }
}
open class ARRAY(private val arrayType: Type): Identifier(Type.ARRAY) {
    fun getArrayType(): Type {
        return arrayType
    }
    final override fun getBaseType(): Identifier {
        return this
    }
}
class IDENT(type: Type, private val name: String) : Identifier(type) {
    override fun toString(): String {
        return name
    }
}



//literals
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

open class StringType : ARRAY(Type.CHAR) {
     override fun getType(): Type {
        return Type.ARRAY
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

//ARGS(),
//FUNC(),
//STAT(),
//PROGRAM(-1),
//NONE();