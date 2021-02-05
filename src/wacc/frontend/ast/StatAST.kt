package wacc.frontend.ast

import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST

interface StatAST : AST

class SkipStatAST : StatAST

class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST

class AssignStatAST(val lhs: LhsAST, val rhs: RhsAST) : StatAST

class ReadStatAST(val expr: LhsAST) : StatAST

class ActionStatAST(val action: Action, val expr: ExprAST) : StatAST

enum class Action {
    FREE, RETURN, EXIT, PRINT, PRINTLN
}

class IfStatAST(val cond: ExprAST, val thenBody: StatAST, val elseBody: StatAST) : StatAST

class WhileStatAST(val cond: ExprAST, val body: StatAST) : StatAST

class MultiStatAST(val stat1: StatAST, val stat2: StatAST) : StatAST
