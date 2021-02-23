package wacc.backend.instruction

class Directive(val directive:String, val value:String=""): Instruction{
    override fun toAssembly(): String {
        var assembly = ".$directive $value"
        if (directive in listOf("global main", "data", "text")){
            return assembly
        }
        return "\t$assembly"
    }
}