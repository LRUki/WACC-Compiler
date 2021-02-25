package wacc.backend

import wacc.backend.instruction.DataDirective
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.StringLabels
import wacc.backend.instruction.instrs.Label
import wacc.backend.instruction.utils.CLibrary
import wacc.backend.instruction.utils.RuntimeError
import wacc.frontend.ast.program.ProgramAST

object CodeGenerator {

    var dataDirective : DataDirective = DataDirective(StringLabels(mutableListOf()));
    var CLib : CLibrary = CLibrary()
    var runtimeErrors: RuntimeError = RuntimeError()
    var labelNumber: Int = 0

    fun getNextLabel(): Label {
        return Label("L${labelNumber++}")
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

fun generateCode(ast: ProgramAST): List<Instruction> {
    // TODO: implement real code gen
    val result = ast.translate()
    return result
//    return AssemblyCode(emptyList())
}
    