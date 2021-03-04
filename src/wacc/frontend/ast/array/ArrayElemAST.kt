package wacc.frontend.ast.array

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.RuntimeError
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.ShiftType
import wacc.backend.translate.instruction.AddInstr
import wacc.backend.translate.instruction.BranchInstr
import wacc.backend.translate.instruction.LoadInstr
import wacc.backend.translate.instruction.MoveInstr
import wacc.backend.translate.instruction.instructionpart.*
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Array Element
 *
 * @property ident Identifier for the array
 * @property indices Expressions indexing into the array
 */
class ArrayElemAST(val ident: IdentAST, val indices: List<ExprAST>) : ExprAST, LhsAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!ident.check(table)) {
            return false
        }
        val arrayType = ident.getRealType(table)
        if (arrayType !is ArrayTypeAST) {
            semanticError("Expected type $arrayType ARRAY, Actual type $arrayType", ctx)
            return false
        }
        // arrayType is implicitly cast to ArrayTypeAST
        if (indices.size != arrayType.dimension) {
            semanticError("Invalid assignment of $arrayType ARRAY," +
                    "Expected dimension ${arrayType.dimension}, Actual dimension ${indices.size}", ctx)
            return false
        }
        indices.forEach {
            if (!it.check(table)) {
                return false
            }
        }
        return true
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val typeAST = ident.getRealType(table) as ArrayTypeAST
        return if (typeAST.dimension > indices.size) {
            ArrayTypeAST(typeAST.type, typeAST.dimension - indices.size)
        } else {
            typeAST.type
        }
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitArrayElemAST(this)
    }

}
