package wacc.frontend.identifierObjs

/* Types should store something (...) */
enum class Type(){
    INT(),
    BOOL(),
    CHAR(),
    PAIR(),
    ARRAY(),

//    STRING(),
//    ARGS(),
//    FUNC(),
//    STAT(),
//    PROGRAM(-1),
//    NONE();
}

open class Identifier(val type: Type) {

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
//INTLIT
//BOOLLIT
//CHARLIT
//PAIRLIT
// STRING(){}
//ARGS(),
//FUNC(),
//STAT(),
//PROGRAM(-1),
//NONE();