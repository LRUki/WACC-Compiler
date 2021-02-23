package wacc.backend.instruction

class PushInstr(val registers: List<Register>): Instruction {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }

//    Push registers onto stack PUSH <reglist>
//    Push LR and registers onto stack PUSH <reglist, LR>
}