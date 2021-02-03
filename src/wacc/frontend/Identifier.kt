package wacc.frontend

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


open class Identifier(val type:Type) {

    open fun getBaseType():Type{
        return type
    }
}
open class INT(){}
open class BOOL(){}
open class CHAR(){}
open class PAIR(){}
open class ARRAY(){}


//literals
open class STRING(){}
//ARGS(),
//FUNC(),
//STAT(),
//PROGRAM(-1),
//NONE();