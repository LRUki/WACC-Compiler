package wacc.frontend.ast

interface StatAST : AstNode

class DeclareStatAST : StatAST

class MultiStatAST(val stat1: StatAST, val stat2: StatAST) : StatAST

class IfStatAST : StatAST