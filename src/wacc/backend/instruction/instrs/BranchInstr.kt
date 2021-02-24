package wacc.backend.instruction.instrs

import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Register

class BranchInstr(val condition: Condition?,
                  val label: LabelInstr, val link : Boolean): Instruction {
    override fun toAssembly(): String {
        var instr = "B"
        if(link){
            instr += "L"
        }
        if (condition != null){
            instr += condition.toAssembly()
        }

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