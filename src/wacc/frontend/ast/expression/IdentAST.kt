package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.getNextFreeCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.MemoryType
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.LoadInstr
import wacc.backend.instruction.utils.RegisterAddrWithOffset
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.type.*
import wacc.frontend.exception.semanticError

/**
 * AST node to represent an Identifier
 *
 * @property name Name of the identifier
 */
class IdentAST(val name: String) : ExprAST, LhsAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        val stEntry = table.lookupAll(name)
        if (stEntry.isEmpty) {
            semanticError("Variable $name has not been declared", ctx)
            return false
        }
        return true
    }

    override fun toString(): String {
        return name
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        val typeOpt = table.lookupAll(name)
        if (typeOpt.isEmpty) { //should never happen because check is called before getRealType
            semanticError("Variable $name has not been declared", ctx)
            throw RuntimeException("Undeclared variable should have been spotted before getting type")
        }
        return when (val type = typeOpt.get()) {
            is FuncAST -> type.type
            is DeclareStatAST -> type.type
            is ParamAST -> type.type
            is ArrayTypeAST -> type
            is PairTypeAST -> type
            else -> throw RuntimeException("Unknown class implementing Identifiable")

        }
    }

    override fun translate(): List<Instruction> {
        val offset = symTable.findOffsetInStack(name)
        var memType : MemoryType? = null
        val type = this.getRealType(symTable)
        if (type == BaseTypeAST(BaseType.BOOL)) {
            memType = MemoryType.SB
        }
        return listOf(LoadInstr(getNextFreeCalleeReg(), memType, RegisterAddrWithOffset(Register.SP, offset, false), Condition.AL))
    }
}
