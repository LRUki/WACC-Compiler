package wacc.frontend

import wacc.frontend.ast.TypeAST
import wacc.frontend.identifiers.Identifier
import java.util.*

class SymbolTable(private val encSymbolTable: SymbolTable?) {

    companion object {
        lateinit var currentST : SymbolTable
    }

    // A symbol table consists of a HashMap and a list of children.
    val currSymbolTable: HashMap<String, Identifier> = HashMap()

    // Gets the top most symbol table
    fun getTopSymbolTable(): SymbolTable {
        var currentSymbolTable: SymbolTable = this
        while (currentSymbolTable.encSymbolTable != null) {
            currentSymbolTable = currentSymbolTable.encSymbolTable!!
        }
        return currentSymbolTable
    }

    fun lookup(name:String) : Optional<Identifier> {
        val value = currSymbolTable[name]
        if (value != null) {
            return Optional.of(value)
        }
        return Optional.empty()
    }

    fun lookupAll(name:String) : Optional<Identifier> {
        val value = lookup(name)
        if (value.isEmpty){
            if (encSymbolTable != null) {
                return encSymbolTable.lookupAll(name)
            }
        }
        return value
    }

    fun add(name:String, obj: Identifier) {
        currSymbolTable[name] = obj
    }

}