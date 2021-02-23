package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Register

class PopInstr(val registers: List<Register>) : Instruction {


    //    Pop registers from stack POP <reglist>
    //    Pop registers and PC from stack POP <reglist, PC>

}