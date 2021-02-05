package wacc.frontend.identifiers

/* Types should store something (...) */
enum class Type(){
    INT,
    BOOL,
    CHAR,
    STRING,
    PAIR,
    ARRAY,
    NULL
//    PROGRAM
//    STRING(),
//    ARGS(),
//    FUNC(),
//    STAT(),
//    NONE();
}

open class INT : Identifier(Type.INT)
open class BOOL : Identifier(Type.BOOL)
open class CHAR : Identifier(Type.CHAR)

open class STRING : Identifier(Type.STRING)
//open class StringType : ARRAY(Type.CHAR) {
//    override fun getType(): Type {
//        return Type.ARRAY
//    }
//}

open class PAIR(val first: Identifier,   val second: Identifier) : Identifier(Type.PAIR)

open class ARRAY(private val arrayType: Type, dimension: Int) : Identifier(Type.ARRAY)
