package wacc.frontend.ast

import wacc.frontend.ast.assign.RhsAST

interface LiterAST: ExprAST

class IntLiterAST(val value: Int): LiterAST

class BoolLiterAST(val value: Boolean): LiterAST

class StrLiterAST(val value: String): LiterAST

class CharLiterAST(val value: Char): LiterAST

class NullPairLiterAST: LiterAST

class ArrayLiterAST(val values: List<ExprAST>): RhsAST
