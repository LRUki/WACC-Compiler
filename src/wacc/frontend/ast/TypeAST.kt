package wacc.frontend.ast

interface TypeAST : AST

class BaseTypeAST(val type: BaseType) : TypeAST

enum class BaseType {
    INT, BOOL, CHAR, STRING
}

class ArrayTypeAST(val type: BaseType) : TypeAST

class PairTypeAST(val type1: BaseType, val type2: BaseType) : TypeAST

