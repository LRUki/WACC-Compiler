package wacc.backend.translate.instr

import wacc.backend.translate.instr.enums.Condition

class BranchInstr(val condition: Condition,
                  val label: LabelInstr, val link: Boolean) : Instr {
    override fun toAssembly(): String {
        var instr = "B"
        if (link) {
            instr += "L"
        }
        instr += condition.toAssembly()
        return instr + " " + label.name
    }


//    Conditional B{cond} <label>
//    Unconditional B <label>
//    Branch with link BL <label> Saves (PC+4) in LR and jumps to function

// below arent needed?
//    Branch, link and exchange BLX <label>
//    Branch, link and exchange BLX <Rm>
//    Branch and exchange BX <Rm>
}