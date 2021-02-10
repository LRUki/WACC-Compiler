package wacc.frontend.ast

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.SymbolTable
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.TypeAST

interface AST {
    fun check(table: SymbolTable): Boolean {
        return true
    }
    fun checkAndGetType(table: SymbolTable): TypeAST? {
       return null
    }
}

abstract class AbstractAST : AST {
    lateinit var ctx: ParserRuleContext
    lateinit var confirmedType : BaseType
}

