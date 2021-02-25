package wacc.frontend.ast.statement.nonblock

import wacc.backend.CodeGenerator
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.StoreInstr
import wacc.backend.instruction.utils.RegisterAddrWithOffset
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.NewPairRhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.ArrayLiterAST
import wacc.frontend.ast.expression.ExprAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.StrLiterAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST
import wacc.frontend.ast.type.*
import wacc.frontend.exception.semanticError

/**
 * AST node representing a Declare Statement (TYPE IDENT = RHS)
 * Create a new variable with a given type and assigns it the given value
 *
 * @property type Type of the variable
 * @property ident Name of the variable
 * @property rhs Value to be stored in the variable
 */
class DeclareStatAST(val type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST, Identifiable, AbstractAST() {
    lateinit var stringLabel: String ;

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!rhs.check(table)) {return false}
        val identName = table.lookup(ident.name)
        val rhsType = rhs.getRealType(table)
        if (identName.isPresent && identName.get() !is FuncAST) {
            semanticError("Variable $ident already exists", ctx)
            return false
        }

        if (!type.equals(rhsType)) {
            semanticError("Type mismatch - Expected type $type, Actual type $rhsType", ctx)
            return false
        }
        table.add(ident.name, this)
        return true
    }

    fun getBytesOfType(): Int {
        return when (type) {
            is BaseTypeAST -> {
                when (type.type) {
                    BaseType.INT, BaseType.STRING -> 4
                    BaseType.CHAR, BaseType.BOOL -> 1
                }
            }
            is ArrayTypeAST, is PairTypeAST -> 4
            else -> 0
        }
    }

    override fun translate(): List<Instruction> {
        val instruction = mutableListOf<Instruction>()
        if (rhs is StrLiterAST) {
             stringLabel = CodeGenerator.dataDirective.addStringLabel(rhs.value)
        }
        instruction.addAll(rhs.translate())
        val size = getBytesOfType()
        symTable.offsetSize -= size
        instruction.add(StoreInstr(Register.R4, null, RegisterAddrWithOffset(Register.SP, symTable.offsetSize, true), Condition.AL))
        return instruction
    }
}