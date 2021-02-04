package wacc.frontend.ast

interface StatAST : AST

class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST

class MultiStatAST(val stat1: StatAST, val stat2: StatAST) : StatAST

class IfStatAST : StatAST