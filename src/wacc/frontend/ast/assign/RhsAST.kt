package wacc.frontend.ast.assign

import wacc.frontend.ast.AST

interface RhsAST : AST

class NewPairRhsAST : RhsAST
class CallRhsAST : RhsAST
