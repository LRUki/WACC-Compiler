package wacc.frontend.ast

import java.awt.Dimension

interface TypeAST : AST

class BaseTypeAST(val type: BaseType) : TypeAST

enum class BaseType {
    INT, BOOL, CHAR, STRING
}

class ArrayTypeAST(val type: TypeAST, val dimension: Int) : TypeAST

class PairTypeAST(val type1: TypeAST, val type2: TypeAST) : TypeAST

class InnerPairTypeAST : TypeAST // For pairElemType: baseType PAIR ;
