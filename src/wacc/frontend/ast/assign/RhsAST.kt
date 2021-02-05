package wacc.frontend.ast.assign

import wacc.frontend.ast.AST
import wacc.frontend.ast.ExprAST

interface RhsAST : AST

class NewPairRhsAST(val fst: ExprAST, val snd: ExprAST) : RhsAST
class CallRhsAST : RhsAST
