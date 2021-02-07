package wacc.frontend.ast.assign

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.PairTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.Typed
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

interface RhsAST : AST, Typed

class NewPairRhsAST(val fst: ExprAST, val snd: ExprAST) : RhsAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRealType(): TypeAST {
        return PairTypeAST(fst.getRealType(), snd.getRealType())
    }

}

class CallRhsAST(val ident: IdentAST, val argList: List<ExprAST>) : RhsAST {
    override fun check(table: SymbolTable): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRealType(): TypeAST {
        return ident.getRealType()
        //Look up in the top level symbol table for the function return type
    }

}
