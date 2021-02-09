package wacc.frontend

import wacc.frontend.ast.Identifiable
import wacc.frontend.ast.function.FuncAST
import java.util.*

open class SymbolTable(private val encSymbolTable: SymbolTable?) {

    // A symbol table consists of a HashMap and a list of children.
    val currSymbolTable: HashMap<String, Identifiable> = HashMap()

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
            return Optional.of(value)
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
        currSymbolTable[name] = obj
    }

}

class FuncSymbolTable(encSymbolTable: SymbolTable?, val funcAST : FuncAST): SymbolTable(encSymbolTable)
