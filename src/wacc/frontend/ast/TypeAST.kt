package wacc.frontend.ast

import java.awt.Dimension

interface TypeAST : AST {
}

class BaseTypeAST(val type: BaseType) : TypeAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

enum class BaseType {
    INT, BOOL, CHAR, STRING
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }
}

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST

class InnerPairTypeAST : TypeAST // For pairElemType: baseType PAIR ;
