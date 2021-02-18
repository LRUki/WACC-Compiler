package wacc.backend

class BranchInstruction(private val condition: Condition?, private val label: Label?,
                        private val register: Register?, private val link : Boolean): Instruction {


//    Conditional B{cond} <label>
//    Unconditional B <label>
//    Branch with link BL <label>
//    Branch, link and exchange BLX <label>
//    Branch, link and exchange BLX <Rm>
//    Branch and exchange BX <Rm>
}