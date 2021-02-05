package wacc.frontend

import wacc.frontend.identifierObjs.Identifier
import java.util.*

class SymbolTable(private val encSymbolTable: SymbolTable?) {

    // A symbol table consists of a HashMap and a list of children.
    private val currSymbolTable: HashMap<String, Identifier> = HashMap()
    var childSymbolTables: MutableList<SymbolTable> = mutableListOf()

    // Creating a new symbol table adds it to the parent
    // symbol table's children list (if parent exists)
    init {
        encSymbolTable?.addChildSymbolTable(this)
    }

    // Gets the top most symbol table
    private fun getTopSymbolTable(): SymbolTable {
        var currentSymbolTable: SymbolTable = this
        while (currentSymbolTable.encSymbolTable != null) {
            currentSymbolTable = currentSymbolTable.encSymbolTable!!
        }
        return currentSymbolTable
    }

    private fun lookup(name:String) : Optional<Identifier> {
        val value = currSymbolTable[name]
        if (value != null) {
            return Optional.of(value)
        }
        return Optional.empty()
    }

    private fun lookupAll(name:String) : Optional<Identifier> {
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

    private fun addChildSymbolTable(child:SymbolTable) {
        childSymbolTables.add(child)
    }
}