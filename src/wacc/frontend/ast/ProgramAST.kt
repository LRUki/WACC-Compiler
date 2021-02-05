package wacc.frontend.ast

import wacc.frontend.ast.function.FuncAST

class ProgramAST(val funcList: List<FuncAST>, val stat: StatAST) : AST