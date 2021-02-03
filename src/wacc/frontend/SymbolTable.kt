package wacc.frontend

import wacc.frontend.identifierObjs.Identifier
import java.util.*

class SymbolTable(private val encSymbolTable: SymbolTable?) {

    var table: MutableMap<String, Identifier> = mutableMapOf()

    private fun lookup(name:String) : Optional<Identifier> {
        val value = table[name]
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
        table[name] = obj
    }

}