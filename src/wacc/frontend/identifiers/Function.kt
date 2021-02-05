package wacc.frontend.identifiers

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST

class Function (val name: String,
                val returnType: Identifier,
                val parameters: List<Param>,
                val symbolTable: SymbolTable) : Identifier(Type.NULL)