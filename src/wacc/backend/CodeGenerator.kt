package wacc.backend

import wacc.backend.instruction.DataDirective
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.StringLabels
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.Label
import wacc.backend.instruction.utils.CLibrary
import wacc.backend.instruction.utils.RuntimeError
import wacc.frontend.ast.program.ProgramAST

object CodeGenerator {

    var dataDirective: DataDirective = DataDirective(StringLabels(mutableListOf()));
    var CLib: CLibrary = CLibrary()
    var runtimeErrors: RuntimeError = RuntimeError()
    var labelNumber: Int = 0

    val resultRegisters = mutableListOf(Register.R0, Register.R1)
    val argumentRegisters = mutableListOf(Register.R2, Register.R3)
    val calleeSavedRegisters = mutableListOf(Register.R4, Register.R5, Register.R6, Register.R7, Register.R8, Register.R9, Register.R10, Register.R11)

    var freeResultRegs = resultRegisters
    var freeArgumentRegs = argumentRegisters
    var freeCalleeSavedRegs = calleeSavedRegisters

    fun getNextLabel(): Label {
        return Label("L${labelNumber++}")
    }

    fun seeNextFreeCalleeReg(): Register {
        //TODO(Handle the case when registers are all used up)
        return calleeSavedRegisters[0]
    }

    fun getNextFreeCalleeReg(): Register {
        //TODO(Handle the case when registers are all used up)
        return calleeSavedRegisters.removeAt(0)
    }

    fun freeCalleeReg(reg: Register) {
        calleeSavedRegisters.add(reg)
        calleeSavedRegisters.sort()
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
    