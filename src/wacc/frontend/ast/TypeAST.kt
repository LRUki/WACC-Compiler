package wacc.frontend.ast


interface TypeAST : AST {
    fun equals()
}

class BaseTypeAST(val type: BaseType) : TypeAST {
    override fun equals() {
        TODO("Not yet implemented")
    }
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

enum class BaseType {
    INT, BOOL, CHAR, STRING, ANY
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST {
    override fun equals() {
        TODO("Not yet implemented")
    }

    //compare dimension here
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST {
    override fun equals() {
        TODO("Not yet implemented")
    }

    // innerpair types
//    pair (pair (1, 2)) 3
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}
//TODO think about this later
class InnerPairTypeAST : TypeAST // For pairElemType: baseType PAIR ;
{
    override fun equals() {
        TODO("Not yet implemented")
    }

    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}
