package wacc.frontend

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
                is ArrayTypeAST, is PairTypeAST -> 4
                else -> 0
            }
        }
    }

    // A symbol table consists of a HashMap and a list of children.
    val currSymbolTable: LinkedHashMap<String, Pair<Identifiable, Int>> = LinkedHashMap()
    var offsetSize: Int = 0

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
            is ParamAST -> { //TODO() Check if this is needed
                getBytesOfType(obj.type)
            }
            is PairTypeAST -> {
                TODO()
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

    fun findOffsetInStack(ident: String): Int {
        var offset = 0
        for ((k, v) in currSymbolTable) {
            if (k == ident) {
                break
            }
            offset += v.second
        }
        return offset
    }
}

class FuncSymbolTable(encSymbolTable: SymbolTable?, val funcAST: FuncAST) : SymbolTable(encSymbolTable)
