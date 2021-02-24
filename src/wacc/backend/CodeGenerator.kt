package wacc.backend

import wacc.backend.instruction.Label
import wacc.frontend.ast.program.ProgramAST

object CodeGenerator {
    var labelNumber: Int = 0

    fun getNextLabel(): Label {
        return Label("L${labelNumber++}:")
    }

    //    private fun translateStatement(stat: StatAST): List<Instruction> {
//            SkipStatAST -> mutableListOf()
//            DeclareStatAST -> translateDeclare()
//            AssignStatAST -> translateAssign()
//            ReadStatAST -> translateRead()
//
//            ActionStat.FREE -> translateFree()
//            ActionStat.RETURN -> translateReturn()
//            ActionStat.EXIT -> translateExit()
//            ActionStat.PRINT -> translatePrint()
//            ActionStat.PRINTLN -> translatePrintLn()

//            IfStatAST -> translateIf()
//            WhileStatAST -> translateWhile)
//            BlockStatAST -> translateStatBlock()
//            MultiStatAST -> translateStatMulti()

//        }

}

fun generateCode(ast: ProgramAST): AssemblyCode {
    // TODO: implement real code gen
    return AssemblyCode(emptyList())
}
