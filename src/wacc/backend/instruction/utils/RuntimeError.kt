package wacc.backend.instruction.utils

import wacc.backend.CodeGenerator
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*

class RuntimeError {

    private val EXIT_CODE = -1
    private var runtimeError: List<Instruction>? = null
    private var nullReferenceError: List<Instruction>? = null
    private var divideZeroError: List<Instruction>? = null
    private var checkArrayBounds: List<Instruction>? = null
    private var overflowError: List<Instruction>? = null

    /* labels for each type of error. */
    companion object {
        val throwRuntimeErrorLabel = Label("p_throw_runtime_error")
        val nullReferenceLabel = Label("p_check_null_pointer")
        val divideZeroCheckLabel = Label("p_check_divide_by_zero")
        val checkArrayBoundsLabel = Label("p_check_array_bounds")
        val throwOverflowErrorLabel = Label("p_throw_overflow_error")
        val exitLabel = Label("exit")
    }

    enum class ErrorType(val msg: String) {
        NULL_REFERENCE("NullReferenceError: attempt to dereference a null reference\n" + 0.toChar()),
        DIVIDE_BY_ZERO("DivideByZeroError: divide or modulo by zero\n" + 0.toChar()),
        LARGE_ARRAY_INDEX_OUT_OF_BOUNDS("ArrayIndexOutOfBoundsError: index too large\n" + 0.toChar()),
        NEGATIVE_ARRAY_INDEX_OUT_OF_BOUNDS("ArrayIndexOutOfBoundsError: negative index\n" + 0.toChar()),
        OVERFLOW_ERROR("OverflowError: the result is too small/large to store in a 4-byte signed-integer.\n");

        override fun toString(): String {
            return msg
        }
    }

    fun translate(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        runtimeError?.let { instructions.addAll(it) }
        nullReferenceError?.let {instructions.addAll(it)}
        divideZeroError?.let { instructions.addAll(it) }
        checkArrayBounds?.let { instructions.addAll(it) }
        overflowError?.let { instructions.addAll(it) }
        return instructions
    }


    fun addThrowRuntimeError() {
        if (runtimeError == null) {
            runtimeError = listOf(
                    throwRuntimeErrorLabel,
                    BranchInstr(Condition.AL, Label(CLibrary.Call.PRINT_STRING.toString()), true),
                    MoveInstr(Condition.AL, Register.R0, ImmediateOperandInt(EXIT_CODE)),
                    BranchInstr(Condition.AL, exitLabel, true)
            )
            CodeGenerator.CLib.addCode(CLibrary.Call.PRINT_STRING)
            // p_throw_runtime_error:
            //   BL p_print_string
            //   MOV r0, #-1
            //   BL exit
        }
    }

    fun addNullReferenceCheck() {
        if (nullReferenceError == null) {
            val errorMsgLabel = CodeGenerator.dataDirective.addStringLabel(ErrorType.NULL_REFERENCE.toString())
            nullReferenceError = listOf(
                    nullReferenceLabel,
                    PushInstr(Register.LR),
                    CompareInstr(Register.R0, null,0),
                    LoadInstr(Register.R0, null,  ImmediateLabel(errorMsgLabel), Condition.EQ),
                    BranchInstr(Condition.EQ, throwRuntimeErrorLabel, true),
                    PopInstr(Register.PC)
            )
        }
        addThrowRuntimeError()
//        p_check_null_pointer:
//        PUSH {lr}
//        CMP r0, #0
//        LDREQ r0, =msg_7
//        BLEQ p_throw_runtime_error
//        POP {pc}
    }

    fun addDivideByZeroCheck() {
        if (divideZeroError == null) {
            val errorMsgLabel = CodeGenerator.dataDirective.addStringLabel(ErrorType.DIVIDE_BY_ZERO.toString())
            divideZeroError = listOf(
                    divideZeroCheckLabel,
                    PushInstr(Register.LR),
                    CompareInstr(Register.R1, null, 0),
                    LoadInstr(Register.R0, null,  ImmediateLabel(errorMsgLabel), Condition.EQ),
                    BranchInstr(Condition.EQ, throwRuntimeErrorLabel, true),
                    PopInstr(Register.PC)
            )
        }
        addThrowRuntimeError()
        // p_check_divide_by_zero:
        //   PUSH {lr}
        //   CMP r1, #0
        //   LDREQ r0, =msg_0
        //   BLEQ p_throw_runtime_error
        //   POP {pc}
    }

    fun addArrayBoundsCheck(){
        if (checkArrayBounds == null) {
            val negativeMsgLabel = CodeGenerator.dataDirective.addStringLabel(ErrorType.NEGATIVE_ARRAY_INDEX_OUT_OF_BOUNDS.toString())
            val tooLargeMsgLabel = CodeGenerator.dataDirective.addStringLabel(ErrorType.LARGE_ARRAY_INDEX_OUT_OF_BOUNDS.toString())

            checkArrayBounds = listOf(
                    checkArrayBoundsLabel,
                    PushInstr(Register.LR),
                    CompareInstr(Register.R0, null, 0),
                    LoadInstr(Register.R0, null,  ImmediateLabel(negativeMsgLabel), Condition.LT),
                    BranchInstr(Condition.LT, throwRuntimeErrorLabel, true),
                    LoadInstr(Register.R1, null, RegisterAddr(Register.R1), Condition.AL),
                    CompareInstr(Register.R0, Register.R1, null),
                    LoadInstr(Register.R0, null,  ImmediateLabel(tooLargeMsgLabel), Condition.CS),
                    BranchInstr(Condition.CS, throwRuntimeErrorLabel, true),
                    PopInstr(Register.PC)
            )
            addThrowRuntimeError()
            // p_check_array_bounds:
            //   PUSH {lr}
            //   CMP r0, #0
            //   LDRLT r0, =msg_0
            //   BLLT p_throw_runtime_error
            //   LDR r1, [r1]
            //   CMP r0, r1
            //   LDRCS r0, =msg_1
            //   BLCS p_throw_runtime_error
            //   POP {pc}
        }



    }

    fun addOverflowError() {
        if (overflowError == null) {
            val errorMsg = CodeGenerator.dataDirective.addStringLabel(ErrorType.OVERFLOW_ERROR.toString())
            overflowError = listOf(
                    throwOverflowErrorLabel,
                    LoadInstr(Register.R0, null,  ImmediateLabel(errorMsg), Condition.LT),
                    BranchInstr(Condition.AL, throwRuntimeErrorLabel, true),
            )
        }
        addThrowRuntimeError()
        // p_throw_overflow_error:
        //   LDR r0, =msg_0
        //   BL p_throw_runtime_error
    }


}