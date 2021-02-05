package wacc.frontend.identifiers

import kotlin.String

open class Identifier(val type: Type) {
//    open fun getBaseType(): Identifier {
//        return this
//    }
}

class Variable(type: Type) : Identifier(type)

//ARGS(),
//FUNC(),
//STAT(),
//PROGRAM(-1),
//NONE();