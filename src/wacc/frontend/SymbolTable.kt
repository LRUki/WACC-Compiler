package wacc.frontend

import wacc.frontend.ast.assign.LhsAST
import wacc.frontend.ast.expression.IdentAST
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.function.ParamAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
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
                else -> 0
            }
        }
    }

    // A symbol table consists of a HashMap and a list of children.
    val currSymbolTable: LinkedHashMap<String, Pair<Identifiable, Int>> = LinkedHashMap()
    var offsetSize: Int = 0
    var startingOffset: Int = 0
    var increaseOffsetForCall = 0 /* Accounts for return address setting up function parameters */
    var callOffset = 0 /* Accounts for negative stack pointer values when setting up parameters */

    // Gets the top most symbol table
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
            return Optional.of(value.first)
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

    fun lookupFirstFunc(): Optional<FuncAST> {
        if (this is FuncSymbolTable) {
            return Optional.of(this.funcAST)
        }
        if (encSymbolTable == null) {
            return Optional.empty()
        }
        return encSymbolTable.lookupFirstFunc()
    }

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
            else -> 0
        }
        currSymbolTable[name] = Pair(obj, size)

    }


    fun getStackOffset(): Int {
        var offset = 0
        currSymbolTable.forEach { (name, type) ->
            if (type.first is DeclareStatAST) {
                offset += type.second
            }
        }
        offsetSize = offset
        return offset
    }

    fun getFuncStackOffset(): Int {
        if (this is FuncSymbolTable) {
            return startingOffset
        }
        if (encSymbolTable != null) {
            return encSymbolTable.getFuncStackOffset()
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
                /* Parameter offset only needed when there are declared variables in the current
                *  scope or inside any inner scope (signified by flag) */
                if ((currSymbolTable.size > funcAST.paramList.size) || innerScopeHasVar) {
                    var offset = 0
                    /* Removes all parameters of the function and
                    * then sums the offset of the remaining identifiers */
                    currSymbolTable.toList()
                            .dropWhile { it.second.first is ParamAST }
                            .forEach { offset += it.second.second }
                    return offset + offsetCounter
                }
            }
            return 0
        }
        /* Keeps checking the enclosing symbol table until the identifier is found */
        if (encSymbolTable != null) {
            /* Sums offsets of all entries in current symbol table to add to final offset  */
            var offset = 0
            currSymbolTable.toList().forEach { offset += it.second.second }
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
        currSymbolTable.values.forEach { totalOffset += it.second }
        var paramOffset = 0
        /* Computes offset being the summed offset of remaining symbol table entries
        * For parameters it is the sum of entries up till that point */
        for ((k, v) in currSymbolTable) {
            if (k == ident && v.first is ParamAST) {
                return paramOffset + pointerOffset
            }
            totalOffset -= v.second
            if (k == ident) {
                return totalOffset + increaseOffsetForCall
            }
            paramOffset += v.second
        }
        if (encSymbolTable != null) {
            /* Searches enclosing symbol table when not found in current table.
            * Includes offset off all entries in current table */
            return encSymbolTable.findOffsetInStack(ident) + paramOffset
        }
        return totalOffset

    }

    /**
     * Decreases offset
     *
     * @param lhs
     * @param rhsType
     */
    fun decreaseOffset(lhs: LhsAST, rhsType: TypeAST) {
        val size = getBytesOfType(rhsType)
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
     * Find s t with identifier
     *
     * @param ident
     * @param correctType
     * @param offset
     * @return
     */

    private fun findSTWithIdentifier(ident: String, correctType: TypeAST, offset: Int): Pair<SymbolTable, Int> {
        if (currSymbolTable.containsKey(ident)) {
            val identAst = currSymbolTable[ident]?.first
            if ((identAst is ParamAST) ||
                    ((identAst is DeclareStatAST) && (identAst.type == correctType))) {
                return Pair(this, offset)
            }
        }
        if (encSymbolTable != null) {
            return encSymbolTable.findSTWithIdentifier(ident, correctType, offset + startingOffset)
        }
        throw RuntimeException("$ident is not present in any symbol table, semantic check failed")
    }

    /**
     * Gets the symbol table containing the provided identifier which will then be used for scoping
     *
     * @param ident
     * @param correctType
     * @return
     */
    fun getSTWithIdentifier(ident: String, correctType: TypeAST): Pair<SymbolTable, Int> {
        return findSTWithIdentifier(ident, correctType, 0)
    }

}

class FuncSymbolTable(encSymbolTable: SymbolTable?, val funcAST: FuncAST) : SymbolTable(encSymbolTable)
