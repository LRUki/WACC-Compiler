package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Register

class BranchInstr(val condition: Condition?, val label: Label?,
                  val register: Register?, val link : Boolean): Instruction {


//    Conditional B{cond} <label>
//    Unconditional B <label>
//    Branch with link BL <label>
//    Branch, link and exchange BLX <label>
//    Branch, link and exchange BLX <Rm>
//    Branch and exchange BX <Rm>
}