package wacc.backend.instruction

class PopInstr(val register: Register) : Instruction {
    override fun toAssembly(): String {
        return "POP {${register.toAssembly()}}"
    }


    //    Pop registers from stack POP <reglist>
    //    Pop registers and PC from stack POP <reglist, PC>

}

fun regsToPopInstrs(list: List<Register>) : List<PopInstr> {
    return list.map { reg -> PopInstr(reg) }
}
