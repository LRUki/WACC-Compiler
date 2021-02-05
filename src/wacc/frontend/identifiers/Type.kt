package wacc.frontend.identifiers

/* Types should store something (...) */
enum class Type(){
    INT(),
    BOOL(),
    CHAR(),
    PAIR(),
    ARRAY(),
    NONE(),
    PROGRAM()
//    STRING(),
//    ARGS(),
//    FUNC(),
//    STAT(),

//    NONE();
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

open class StringType : ARRAY(Type.CHAR) {
    override fun getType(): Type {
        return Type.ARRAY
    }
}