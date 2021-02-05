package wacc.frontend.ast

interface LiterAST: ExprAST

class IntLiterAST(val value: Int): LiterAST

class StrLiterAST(val value: String): LiterAST
