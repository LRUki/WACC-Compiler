package wacc.backend.instruction

class PopInstr(val register: Register) : Instruction {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }


    //    Pop registers from stack POP <reglist>
    //    Pop registers and PC from stack POP <reglist, PC>

}