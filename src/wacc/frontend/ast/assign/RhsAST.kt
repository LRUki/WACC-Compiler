package wacc.frontend.ast.assign

import wacc.frontend.ast.AST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.Typed
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST

interface RhsAST : AST, Typed

class NewPairRhsAST(val fst: ExprAST, val snd: ExprAST) : RhsAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRealType(): TypeAST {
        TODO("Not yet implemented")
    }

}

class CallRhsAST(val ident: IdentAST, val argList: List<ExprAST>) : RhsAST {
    override fun check(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getRealType(): TypeAST {
        TODO("Not yet implemented")
    }

}
