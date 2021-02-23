package wacc.backend.instruction

class BranchInstr(val condition: Condition?, val label: Label?,
                  val register: Register?, val link : Boolean): Instruction {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }


//    Conditional B{cond} <label>
//    Unconditional B <label>
//    Branch with link BL <label>
//    Branch, link and exchange BLX <label>
//    Branch, link and exchange BLX <Rm>
//    Branch and exchange BX <Rm>
}