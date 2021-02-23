package wacc.backend

import wacc.backend.instruction.Label
import wacc.frontend.ast.program.ProgramAST

object CodeGenerator {
    var labelNumber: Int = 0

    fun getNextLabel(): Label {
        return Label("L${labelNumber++}:")
    }
}

fun generateCode(ast: ProgramAST): AssemblyCode {
    // TODO: implement real code gen
    return AssemblyCode(emptyList())
}
