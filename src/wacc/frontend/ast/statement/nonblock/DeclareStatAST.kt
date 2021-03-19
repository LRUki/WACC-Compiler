package wacc.frontend.ast.statement.nonblock

import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.visitor.AstVisitor
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.assign.StructAssignAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.expression.OpExpr
import wacc.frontend.ast.expression.StructAccessAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
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
class DeclareStatAST(var type: TypeAST, val ident: IdentAST, val rhs: RhsAST) : StatAST, Identifiable, AbstractAST() {
    lateinit var stringLabel: String

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!rhs.check(table)) {
            return false
        }
        if (rhs is StructAccessAST) {
            val structFromIdent = table.lookup(rhs.structIdent.name)
            if (structFromIdent.isEmpty) {
                semanticError("Setting fields for a non-declared struct", ctx)
                return false
            }
            val structUsageInST = structFromIdent.get()
            val structName =
                    if (structUsageInST is ParamAST) {
                        (structUsageInST.type as StructTypeAST).ident.name
                    } else {
                        val structDeclareInST = structFromIdent.get() as DeclareStatAST
                        (structDeclareInST.type as StructTypeAST).ident.name
                    }
            val structInST = table.lookupAll(structName)
            if (structInST.isEmpty || structInST.get() !is StructDeclareAST) {
                semanticError("Declared instance of non-existing type struct $structName", ctx)
                return false
            }
        }

        val identName = table.lookup(ident.name)
        val rhsType = rhs.getRealType(table)
        if (identName.isPresent && identName.get() !is FuncAST) {
            semanticError("Variable $ident already exists", ctx)
            return false
        }

        if (type !is ImplicitTypeAST) {
            if (!type.equals(rhsType)) {
                semanticError("Type mismatch - Expected type $type, Actual type $rhsType", ctx)
                return false
            }
        } else {
            if (rhsType.isConcreteType()) {
                type = rhsType // Replace the type with the inferred type
            } else {
                semanticError("Type inference failed - $rhsType is not a concrete type", ctx)
                return false
            }
        }

        if (type is StructTypeAST) {
            val structInTable = table.lookupAll((type as StructTypeAST).ident.name)
            if (structInTable.isEmpty || structInTable.get() !is StructDeclareAST) {
                semanticError("Struct has not been declared before use", ctx)
                return false
            }
            val structFields = (structInTable.get() as StructDeclareAST).fields
            val structFieldTypes = structFields.map { it.type }
            if (rhs !is StructAssignAST) {
                semanticError("Invalid method of assigning to a struct", ctx)
                return false
            }
            if (structFieldTypes.size > rhs.assignments.size) {
                semanticError("Some fields of the struct are not being assigned", ctx)
                return false
            }
            if (structFieldTypes.size < rhs.assignments.size) {
                semanticError("attempting to assign to more fields than the struct has", ctx)
                return false
            }
            for (i in structFieldTypes.indices) {
                if (!rhs.assignments[i].getRealType(table).equals(structFieldTypes[i])) {
                    semanticError("Invalid assignment: Trying to assign ${rhs.assignments[i].getRealType(table)} to ${structFieldTypes[i]}", ctx)
                    return false
                }
            }
        }
        ident.symTable = table
        table.add(ident.name, this)
        if (rhsType is PointerTypeAST && rhs is OpExpr) {
            (rhs as OpExpr).setMemoryReferencesAccessed()
        }
        if (rhs is CallRhsAST) {
            symTable.setAssignedField(ident.name)
            symTable.setAccessedField(ident.name)
        }

        return true
    }

    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitDeclareStatAST(this)
    }

}