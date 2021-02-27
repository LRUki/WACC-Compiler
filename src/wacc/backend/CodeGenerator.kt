package wacc.backend

import wacc.backend.instruction.DataDirective
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.StringLabels
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.Label
import wacc.backend.instruction.utils.CLibrary
import wacc.backend.instruction.utils.RuntimeError
import wacc.frontend.ast.program.ProgramAST
import java.util.Stack

object CodeGenerator {

    var dataDirective: DataDirective = DataDirective(StringLabels(mutableListOf()));
    var CLib: CLibrary = CLibrary()
    var runtimeErrors: RuntimeError = RuntimeError()
    var labelNumber: Int = 0

    val resultRegisters = mutableListOf(Register.R0, Register.R1)
    val argumentRegisters = mutableListOf(Register.R2, Register.R3)
    val freeCalleeSavedRegs: Stack<Register> = makeStack(listOf(Register.R4, Register.R5, Register.R6, Register.R7, Register.R8, Register.R9, Register.R10, Register.R11))
    val calleSavedRegsInUse: Stack<Register> = makeStack(emptyList())

    var freeResultRegs = resultRegisters
    var freeArgumentRegs = argumentRegisters

    private fun <T> makeStack(list: List<T>): Stack<T> {
        val stack = Stack<T>()
        list.reversed().forEach {
            stack.push(it)
        }
        return stack
    }

    fun getNextLabel(): Label {
        return Label("L${labelNumber++}")
    }

    fun seeNextFreeCalleeReg(): Register {
        if (freeCalleeSavedRegs.isEmpty()) {
            return Register.CPSR// TODO() Change later
        }
        return freeCalleeSavedRegs.peek()
    }

    fun getNextFreeCalleeReg(): Register {
        if (freeCalleeSavedRegs.isEmpty()){
            return Register.CPSR//TODO() CHANGE LATER
        }
        val reg = freeCalleeSavedRegs.pop()
        calleSavedRegsInUse.push(reg)
//        println("Adding ${reg}")
        return reg
    }

    fun getLastUsedCalleeReg(): Register {
        if (calleSavedRegsInUse.isEmpty()) {
            return Register.CPSR//TODO() CHANGE LATER
        }
        return calleSavedRegsInUse.peek()
    }

    fun freeCalleeReg() {
        if (calleSavedRegsInUse.isEmpty()){
            return
        }
        freeCalleeSavedRegs.push(calleSavedRegsInUse.pop())
//        println("removing ${freeCalleeSavedRegs.push(calleSavedRegsInUse.pop())}")
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
    