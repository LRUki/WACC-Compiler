package wacc.frontend

import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import wacc.frontend.ast.statement.nonblock.StructDeclareAST
import wacc.frontend.ast.type.*
import java.util.*
import kotlin.collections.LinkedHashMap

/**
 * Symbol table to map identifiers to a Identifiable AST nodes
 * Stores different information depending on variable/function
 *
 * @property encSymbolTable Parent symbol table
 */
open class SymbolTable(private val encSymbolTable: SymbolTable?) {

    companion object {
        fun getBytesOfType(type: TypeAST): Int {
            return when (type) {
                is BaseTypeAST -> {
                    when (type.type) {
                        BaseType.INT, BaseType.STRING -> 4
                        BaseType.CHAR, BaseType.BOOL -> 1
                    }
                }
                is ArrayTypeAST, is PairTypeAST, is AnyPairTypeAST -> 4
                is PointerTypeAST -> 4
                is StructTypeAST -> 4
                else -> 0
            }
        }
    }

    /** A symbol table consists of a HashMap */
    val currSymbolTable: LinkedHashMap<String, SymbolTableField> = LinkedHashMap()
    var offsetSize: Int = 0

    /** Current offset size, used in DeclareStatAST and IdentAST*/
    var startingOffset: Int = 0

    /** Initial offset of the table, equal to sum of all offsets in table */
    var callOffset = 0
    /** Accounts for negative stack pointer values when setting up parameters */

    /** Gets the top most symbol table */
    fun getTopSymbolTable(): SymbolTable {
        var currentSymbolTable: SymbolTable = this
        while (currentSymbolTable.encSymbolTable != null) {
            currentSymbolTable = currentSymbolTable.encSymbolTable!!
        }
        return currentSymbolTable
    }

    fun lookup(name: String): Optional<Identifiable> {
        val value = currSymbolTable[name]
        if (value != null) {
            return Optional.of(value.identifiable)
        }
        return Optional.empty()
    }

    fun lookupAll(name: String): Optional<Identifiable> {
        val value = lookup(name)
        if (value.isEmpty) {
            if (encSymbolTable != null) {
                return encSymbolTable.lookupAll(name)
            }
        }
        return value
    }

    fun lookupAllInFunc(name: String): Optional<Identifiable> {
        val value = lookup(name)
        if (value.isEmpty) {
            if (encSymbolTable != null) {
                return encSymbolTable.lookupAll(name)
            }
        }
        return value
    }

    fun lookupFirstFunc(): Optional<FuncAST> {
        if (this is FuncSymbolTable) {
            return Optional.of(funcAST)
        }
        if (encSymbolTable == null) {
            return Optional.empty()
        }
        return encSymbolTable.lookupFirstFunc()
    }

    /** Called when the variable is assigned to */
    fun setAccessField(name: String) {
        val value = currSymbolTable[name]
        if (value != null) {
            value.accessFlag = true
            return
        }
        if (encSymbolTable != null) {
            return encSymbolTable.setAccessField(name)
        }
        throw RuntimeException("Trying to test the access flag of a variable not in the symbol table ")
    }

    fun getAccessField(name: String): Boolean {
        val value = currSymbolTable[name]
        if (value != null) {
            return value.accessFlag
        }
        if (encSymbolTable != null) {
            return encSymbolTable.getAccessField(name)
        }
        throw RuntimeException("Trying to get the access flag of a variable not in the symbol table ")
    }

    fun updateConstPropVariable(name: String, identifiable: Identifiable) {
        val value = currSymbolTable[name]
        if (value != null) {
            currSymbolTable[name] = SymbolTableField(identifiable, value.size, true)
            return
        }
        if (encSymbolTable != null) {
            return encSymbolTable.updateConstPropVariable(name, identifiable)
        }
    }

    /**
     * Add an element to the internal representation of the hash table
     * Calculates the size of the object and store that alongside obj
     *
     * @param name Identifier string of the symbol table entry
     * @param obj Object being added
     */
    fun add(name: String, obj: Identifiable) {
        val size: Int = when (obj) {
            is FuncAST -> {
                getBytesOfType(obj.type)
            }
            is DeclareStatAST -> {
                getBytesOfType(obj.type)
            }
            is ArrayTypeAST -> {
                getBytesOfType(obj.type)
            }
            is ParamAST -> {
                getBytesOfType(obj.type)
            }
            is StructDeclareAST -> {
                0
            }
            else -> 0
        }
        currSymbolTable[name] = SymbolTableField(obj, size, false)
    }


    /**
     * Gets the offset in the stack of a variable declared inside a scope
     *
     * @return the offset of that variable in the current symbol table
     */
    fun getStackOffset(): Int {
        var offset = 0
        currSymbolTable.forEach { (name, type) ->
            if (type.identifiable is DeclareStatAST) {
                offset += type.size
            }
        }
        offsetSize = offset
        return offset
    }

    /**
     * Gets the offset the stack pointer in a function was shifted by
     * Equal to the total offset of all variables in the function scope
     *
     * @return the offset needed by the closest function symbol table
     */
    fun getFuncStackOffset(): Int {
        if (this is FuncSymbolTable) {
            return startingOffset
        }
        if (encSymbolTable != null) {
            var offset = 0
            currSymbolTable.forEach { offset += it.value.size }
            return encSymbolTable.getFuncStackOffset() + offset
        }
        throw RuntimeException("Semantic Failure: Return used outside of a function")
    }

    /**
     * Recursive method for finding if identifier is a parameter and returns offset on stack if so
     *
     * @param name Identifier string of the parameter
     * @param innerScopeHasVar Have variables been declared inside the function scope
     * @param offsetCounter Accumulative offset until parameter is reached
     * @return The offset of the provided parameter on the stack
     */
    private fun findIfParamInFuncSymbolTableToAddOffset(name: String, innerScopeHasVar: Boolean, offsetCounter: Int): Int {
        val identAst = lookup(name)
        if (identAst.isPresent) {
            val identValue = identAst.get()
            if ((this is FuncSymbolTable) && (identValue is ParamAST)) {
                /** Parameter offset only needed when there are declared variables in the current
                 *  scope or inside any inner scope (signified by flag) */
                if ((currSymbolTable.size > funcAST.paramList.size) || innerScopeHasVar) {
                    var offset = 0
                    /** Removes all parameters of the function and
                     * then sums the offset of the remaining identifiers */
                    currSymbolTable.toList()
                            .dropWhile { it.second.identifiable is ParamAST }
                            .forEach { offset += it.second.size }
                    return offset + offsetCounter
                }
            }
            return 0
        }
        /** Keeps checking the enclosing symbol table until the identifier is found */
        if (encSymbolTable != null) {
            /** Sums offsets of all entries in current symbol table to add to final offset  */
            var offset = 0
            currSymbolTable.toList().forEach { offset += it.second.size }
            return encSymbolTable.findIfParamInFuncSymbolTableToAddOffset(name, currSymbolTable.size > 0, startingOffset)
        }
        return 0
    }

    /**
     * Calls findIfParamInFuncSymbolTableToAddOffset to compute offset
     *
     * @param name Identifier string in question
     * @return The offset of the provided parameter on the stack
     */
    fun checkParamInFuncSymbolTable(name: String): Int {
        return findIfParamInFuncSymbolTableToAddOffset(name, false, 0)
    }

    /**
     * Finds offset of given identifier in stack
     *
     * @param ident Identifier string of variable in question
     * @return offset of the identifier
     */
    fun findOffsetInStack(ident: String): Int {
        var totalOffset = 0
        val pointerOffset = 4
        currSymbolTable.values.forEach { totalOffset += it.size }
        var paramOffset = 0
        /** Computes offset being the summed offset of remaining symbol table entries
         * For parameters it is the sum of entries up till that point */
        for ((k, v) in currSymbolTable) {
            if (v.identifiable is StructDeclareAST) {
                continue
            }
            if (k == ident && v.identifiable is ParamAST) {
                return paramOffset + pointerOffset
            }
            totalOffset -= v.size
            if (k == ident) {
                return totalOffset
            }
            paramOffset += v.size
        }
        if (encSymbolTable != null) {
            /** Searches enclosing symbol table when not found in current table.
             * Includes offset off all entries in current table */
            return encSymbolTable.findOffsetInStack(ident) + paramOffset
        }
        return totalOffset

    }

    /**
     * Decreases the offsetSize of the table containing the rhs
     * Used in declareStatASTs and IdentASTs
     *
     * @param lhs The Lhs being used in the declareStatAST
     * @param rhsType The type of the rhs to check for re-declaration with a different type
     */
    fun decreaseOffset(lhs: LhsAST, rhsType: TypeAST) {
        val size = getBytesOfType(rhsType)
        /** Recursively calls decreaseOffset when the lhs is an Ident or DeclareStat and the
        type matcs, (i.e it isn't a new variable declared in the inner scope with the
        same name but a different type)*/
        if (lhs is IdentAST) {
            val ident = lookup(lhs.name)
            if (ident.isEmpty) {
                encSymbolTable?.decreaseOffset(lhs, rhsType)
                return
            }
            val identObject = ident.get()
            if ((identObject is DeclareStatAST) && (identObject.type != rhsType)) {
                encSymbolTable?.decreaseOffset(lhs, rhsType)
                return
            }
        }
        offsetSize -= size
    }

    /**
     * Helper for finding the symbol table containing the provided identifier
     *
     * @param ident The identifier string to search for
     * @param correctType The type of the identifier string,
     *                    to ignore re-declarations with a different type
     * @param offset The total offset up until the current symbol table
     * @return Pair of the symbol table with the identifier, and the
     *         total offset up until that point
     */

    private fun findSTWithIdentifier(ident: String, correctType: TypeAST, offset: Int): Pair<SymbolTable, Int> {
        if (currSymbolTable.containsKey(ident)) {
            val identAst = currSymbolTable[ident]?.identifiable
            if ((identAst is ParamAST) ||
                    ((identAst is DeclareStatAST) && (identAst.type == correctType))) {
                return Pair(this, offset)
            }
        }
        if (encSymbolTable != null) {
            var currentSTOffset = 0
            currSymbolTable.forEach { currentSTOffset += it.value.size }
            return encSymbolTable.findSTWithIdentifier(ident, correctType, offset + currentSTOffset)
        }
        throw RuntimeException("$ident is not present in any symbol table, semantic check failed")
    }

    /**
     * Gets the symbol table containing the provided identifier which will then be used for scoping
     *
     * @param ident The identifier string to search for
     * @param correctType The type of the identifier string,
     *                    to ignore re-declarations with a different type
     * @return Pair of the symbol table with the identifier, and the
     *         total offset up until that point
     */
    fun getSTWithIdentifier(ident: String, correctType: TypeAST): Pair<SymbolTable, Int> {
        return findSTWithIdentifier(ident, correctType, 0)
    }

    fun mergeFuncsWithTable(importedST: SymbolTable) {
        for (entry in importedST.currSymbolTable) {
            if (entry.value.identifiable is FuncAST && !currSymbolTable.containsKey(entry.key)) {
                add(entry.key, entry.value.identifiable)
            }
        }
    }

}

class SymbolTableField(val identifiable: Identifiable, val size: Int, var accessFlag: Boolean)

class FuncSymbolTable(encSymbolTable: SymbolTable?, val funcAST: FuncAST) : SymbolTable(encSymbolTable)
