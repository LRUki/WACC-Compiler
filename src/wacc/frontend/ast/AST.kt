package wacc.frontend.ast

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SymbolTable
import wacc.frontend.exception.SemanticException

interface AST {
     fun check(table: SymbolTable): Boolean {
          return true
     }
}

abstract class AbstractAST : AST {
    lateinit var ctx: ParserRuleContext
}

