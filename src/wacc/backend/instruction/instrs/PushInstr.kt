package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Register

class PushInstr(val register: Register): Instruction {
    override fun toAssembly(): String {
        return "PUSH {${register.toAssembly()}}"
    }

//    Push registers onto stack PUSH <reglist>
//    Push LR and registers onto stack PUSH <reglist, LR>
}

fun regsToPushInstrs(list: List<Register>) : List<PushInstr> {
    return list.map { reg -> PushInstr(reg) }
}
