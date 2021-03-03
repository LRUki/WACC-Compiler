package wacc.backend.translate.instr

import wacc.backend.translate.instr.enums.Register

class PushInstr(val register: Register) : Instr {
    override fun toAssembly(): String {
        return "PUSH {${register.toAssembly()}}"
    }

//    Push registers onto stack PUSH <reglist>
//    Push LR and registers onto stack PUSH <reglist, LR>
}

fun regsToPushInstrs(list: List<Register>): List<PushInstr> {
    return list.map { reg -> PushInstr(reg) }
}
