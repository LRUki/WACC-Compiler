package wacc.frontend.ast

interface StatAST : AST

class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST

class IfStatAST(val cond: ExprAST, val thenStat: StatAST, val elseStat: StatAST) : StatAST

class MultiStatAST(val stat1: StatAST, val stat2: StatAST) : StatAST

class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}
