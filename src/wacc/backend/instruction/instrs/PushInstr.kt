package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Register

class PushInstr(val registers: List<Register>): Instruction {

//    Push registers onto stack PUSH <reglist>
//    Push LR and registers onto stack PUSH <reglist, LR>
}