package wacc.frontend.identifierObjs



open class Identifier(private val type: Type) {

    open fun getType(): Type {
        return type
    }
    open fun getBaseType(): Identifier {
        return this
    }


}

class IDENT(type: Type, private val name: String) : Identifier(type) {
    override fun toString(): String {
        return name
    }
}


//ARGS(),
//FUNC(),
//STAT(),
//PROGRAM(-1),
//NONE();