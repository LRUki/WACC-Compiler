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
import java.util.concurrent.atomic.AtomicInteger

class CodeGenerator {

    var dataDirective: DataDirective
    var cLib: CLibrary
    var runtimeErrors: RuntimeErrors
    var labelNumber: AtomicInteger

    var resultRegisters: MutableList<Register>
    var argumentRegisters: MutableList<Register>
    var freeCalleeSavedRegs: Stack<Register>
    var calleSavedRegsInUse: Stack<Register>

    var freeResultRegs: MutableList<Register>
    var freeArgumentRegs: MutableList<Register>

    var useAccumulator = false

    init {
        dataDirective = DataDirective(StringLabels(mutableListOf()))
        cLib = CLibrary(this)
        runtimeErrors = RuntimeErrors(this)
        labelNumber = AtomicInteger(0)

        resultRegisters = mutableListOf(Register.R0, Register.R1)
        argumentRegisters = mutableListOf(Register.R2, Register.R3)
        freeCalleeSavedRegs = makeStack(listOf(Register.R4, Register.R5, Register.R6, Register.R7, Register.R8, Register.R9, Register.R10))
        calleSavedRegsInUse = makeStack(emptyList())

        freeResultRegs = resultRegisters
        freeArgumentRegs = argumentRegisters

        useAccumulator = false
    }

    /** Given a list of objects creates a stack and
     * pushes the objects in reverse order*/
    private fun <T> makeStack(list: List<T>): Stack<T> {
        val stack = Stack<T>()
        list.reversed().forEach {
            stack.push(it)
        }
        return stack
    }

    /** Gets the next free label number using a global counter */
    fun getNextLabel(): Label {
        return Label("L${labelNumber.getAndIncrement()}")
    }

    /** Return at next free callee register available
     *  without modification */
    fun seeNextFreeCalleeReg(): Register {
        if (freeCalleeSavedRegs.isEmpty()) {
            return Register.NONE
        }
        return freeCalleeSavedRegs.peek()
    }

    /** Returns the next free callee register available,
     *  removes it from the free callee reigsters and adds to
     *  in use callee registers */
    fun getNextFreeCalleeReg(): Register {
        if (freeCalleeSavedRegs.isEmpty()) {
            useAccumulator = true
            return Register.NONE
        }
        val reg = freeCalleeSavedRegs.pop()
        calleSavedRegsInUse.push(reg)
        return reg
    }

    /** Returns the most recently used callee register */
    fun seeLastUsedCalleeReg(): Register {
        if (useAccumulator) {
            useAccumulator = false
            return Register.NONE
        }
        return calleSavedRegsInUse.peek()
    }

    /** Frees the most recently used callee register */
    fun freeCalleeReg() {
        if (calleSavedRegsInUse.isEmpty()) {
            return
        }
        freeCalleeSavedRegs.push(calleSavedRegsInUse.pop())
    }

    /** Frees all callee registers */
    fun freeAllCalleeReg() {
        while (!calleSavedRegsInUse.isEmpty()) {
            freeCalleeReg()
        }
    }

    /** swap the first two registers for expression weight */
    fun swapFirstTwoReg() {
        val reg1 = freeCalleeSavedRegs.pop()
        val reg2 = freeCalleeSavedRegs.pop()
        freeCalleeSavedRegs.push(reg1)
        freeCalleeSavedRegs.push(reg2)
    }

    fun clone(): CodeGenerator {
        val codeGenerator = CodeGenerator()

        codeGenerator.dataDirective = this.dataDirective
        codeGenerator.cLib = this.cLib
        codeGenerator.runtimeErrors = this.runtimeErrors
        codeGenerator.labelNumber = this.labelNumber

        return codeGenerator
    }
}

/**
 * Function that is called by backend function in main
 * to generate the intermediate code representation
 *
 * @param ast The main program AST which will be visited first
 * @return The list of instructions generated
 */
fun generateCode(ast: ProgramAST): List<Instruction> {
    return TranslateVisitor().visit(ast)
}
