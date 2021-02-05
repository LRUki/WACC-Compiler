package wacc.frontend.ast

interface LiterAST: ExprAST

class IntLiterAST(val value: Int): LiterAST

class BoolLiterAST(val value: Boolean): LiterAST

class StrLiterAST(val value: String): LiterAST

class CharLiterAST(val value: Char): LiterAST
