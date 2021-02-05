package wacc.frontend.ast.assign

import wacc.frontend.ast.AST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

interface RhsAST : AST

class NewPairRhsAST(val fst: ExprAST, val snd: ExprAST) : RhsAST
class CallRhsAST(val ident: IdentAST, val argList: List<ExprAST>) : RhsAST
