package wacc.frontend.ast

import wacc.frontend.SymbolTable

interface AST {
     fun check(table: SymbolTable): Boolean {
          return true
     }
}