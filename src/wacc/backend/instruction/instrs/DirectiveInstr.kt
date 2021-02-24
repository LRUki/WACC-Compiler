package wacc.backend.instruction.instrs

import wacc.backend.instruction.Instruction

data class DirectiveInstr(var str: String): Instruction {
    override fun toAssembly(): String {
        TODO("Not yet implemented")
    }
}