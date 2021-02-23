package wacc.backend

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.instrs.Label
import wacc.frontend.ast.program.ProgramAST

object CodeGenerator {
    var labelNumber: Int = 0

    fun getNextLabel(): Label {
        return Label("L${labelNumber++}")
    }
}

fun generateCode(ast: ProgramAST): List<Instruction> {
    // TODO: implement real code gen
    val result = ast.translate()
    return result
//    return AssemblyCode(emptyList())
}
    