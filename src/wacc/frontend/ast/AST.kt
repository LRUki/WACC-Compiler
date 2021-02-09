package wacc.frontend.ast

import antlr.WaccParser
import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SymbolTable

interface AST {
     fun check(table: SymbolTable): Boolean {
          return true
     }

     fun getContext(): ParserRuleContext?{
          return null
     }

}