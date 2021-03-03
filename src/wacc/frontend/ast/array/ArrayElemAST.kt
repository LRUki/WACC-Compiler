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

    override fun translate(): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        val stackReg = getNextFreeCalleeReg()
        val stackOffset = symTable.findOffsetInStack(ident.name)
        instrs.add(AddInstr(Condition.AL, stackReg, Register.SP, ImmediateIntOperand(stackOffset), false))
        indices.forEach {
            instrs.addAll(it.translate())
            instrs.add(LoadInstr(Condition.AL, null, RegisterMode(stackReg), stackReg))
            instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(seeLastUsedCalleeReg())))
            instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(stackReg)))
            instrs.add(BranchInstr(Condition.AL, RuntimeError.checkArrayBoundsLabel, true))
            CodeGenerator.runtimeErrors.addArrayBoundsCheck()
            instrs.add(AddInstr(Condition.AL, stackReg, stackReg, ImmediateIntOperand(4), false))
            val identType = ident.getRealType(symTable)
            if (identType is ArrayTypeAST &&
                    ((identType.type.equals(BaseTypeAST(BaseType.CHAR)) || identType.type.equals(BaseTypeAST(BaseType.BOOL))))) {
                instrs.add(AddInstr(Condition.AL, stackReg, stackReg, RegisterOperand(seeLastUsedCalleeReg()), false))
            } else {
                instrs.add(AddInstr(Condition.AL, stackReg, stackReg, RegShiftOffsetOperand(seeLastUsedCalleeReg(), ShiftType.LSL, 2), false))
            }
            freeCalleeReg()
        }
        return instrs
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitArrayElemAST(this)
    }

}
