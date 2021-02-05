package wacc.frontend.ast

import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

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

class BlockStatAST(val body: StatAST) : StatAST

class MultiStatAST(val stats: List<StatAST>) : StatAST
