package wacc.frontend.ast.assign

import wacc.frontend.SemanticAnalyser.Companion.semanticError
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AST
import wacc.frontend.ast.PairTypeAST
import wacc.frontend.ast.TypeAST
import wacc.frontend.ast.Typed
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST

interface RhsAST : AST, Typed

class NewPairRhsAST(val fst: ExprAST, val snd: ExprAST) : RhsAST {
    override fun check(table: SymbolTable): Boolean {
        fst.check(table)
        snd.check(table)
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return PairTypeAST(fst.getRealType(table), snd.getRealType(table))
    }

}

class CallRhsAST(val ident: IdentAST, val argList: List<ExprAST>) : RhsAST {
    override fun check(table: SymbolTable): Boolean {
        ident.check(table)
        val funcAst = table.lookup(ident.name).get()

        if (funcAst !is FuncAST) {
            semanticError("$ident is not a function")
        }
        funcAst as FuncAST
        argList.forEach { it.check((table)) }
        if (funcAst.paramList.size != argList.size) {
            semanticError("Incorrect number of arguments, Expected ${funcAst.paramList.size} " +
                    "arguments but got ${argList.size}")
        }
        for (i in 0 until argList.size) {
            val argType = argList[i].getRealType(table)
            val paramType = funcAst.paramList[i].ident.getRealType(table)
            if (!argType.equals(paramType)) {
                semanticError("Type mismatch, expected type $paramType, actual type $argType")
            }
        }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return ident.getRealType(table)
        //Look up in the top level symbol table for the function return type
    }

}
