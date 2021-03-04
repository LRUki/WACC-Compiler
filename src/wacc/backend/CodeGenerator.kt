package wacc.backend

import wacc.backend.translate.DataDirective
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.StringLabels
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.Label
import wacc.backend.translate.CLibrary
import wacc.backend.translate.RuntimeErrors
import wacc.backend.visitor.TranslateVisitor
import wacc.frontend.ast.program.ProgramAST
import java.util.*

object CodeGenerator {

    var dataDirective: DataDirective = DataDirective(StringLabels(mutableListOf()))
    var cLib: CLibrary = CLibrary()
    var runtimeErrors: RuntimeErrors = RuntimeErrors()
    var labelNumber: Int = 0

    val resultRegisters = mutableListOf(Register.R0, Register.R1)
    val argumentRegisters = mutableListOf(Register.R2, Register.R3)
    val freeCalleeSavedRegs: Stack<Register> = makeStack(listOf(Register.R4, Register.R5, Register.R6, Register.R7, Register.R8, Register.R9, Register.R10))
    val calleSavedRegsInUse: Stack<Register> = makeStack(emptyList())

    var freeResultRegs = resultRegisters
    var freeArgumentRegs = argumentRegisters

    var useAccumulator = false

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
            return Register.NONE
        }
        return freeCalleeSavedRegs.peek()
    }

    fun getNextFreeCalleeReg(): Register {
        if (freeCalleeSavedRegs.isEmpty()) {
            useAccumulator = true
            return Register.NONE
        }
        val reg = freeCalleeSavedRegs.pop()
        calleSavedRegsInUse.push(reg)
        return reg
    }

    fun seeLastUsedCalleeReg(): Register {
        if (useAccumulator) {
            useAccumulator = false

            return Register.NONE
        }
        return calleSavedRegsInUse.peek()
    }

    fun freeCalleeReg() {
        if (calleSavedRegsInUse.isEmpty()) {
            return
        }
        freeCalleeSavedRegs.push(calleSavedRegsInUse.pop())
    }

    fun freeAllCalleeReg() {
        while (!calleSavedRegsInUse.isEmpty()) {
            freeCalleeReg()
        }
    }

}

fun generateCode(ast: ProgramAST): List<Instruction> {
    val result = TranslateVisitor().visit(ast)
    return result
}
