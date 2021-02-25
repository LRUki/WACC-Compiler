package wacc.backend.instruction.utils

import wacc.backend.CodeGenerator
import wacc.backend.instruction.Instruction
//import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.*
import wacc.backend.instruction.instrs.*
class CLibrary {
    enum class LibraryFunctions {
        SCANF,
        PRINTF,
        FFLUSH,
        MALLOC,
        FREE,
        PUTS,
        PUTCHAR;

        override fun toString(): String {
            return super.toString().toLowerCase()
        }
    }

    enum class Call {
        READ_INT,
        READ_CHAR,
        PRINT_INT,
        PRINT_BOOL,
        PRINT_STRING,
        PRINT_REFERENCE,
        PRINT_LN,
        FREE_ARRAY,
        FREE_PAIR;

        override fun toString(): String {
            return "p_${super.toString().toLowerCase()}"
        }
    }

    private val LibraryCalls: HashMap<Call, List<Instruction>> = LinkedHashMap()


    fun addCode(call: Call) {
        if (LibraryCalls.containsKey(call)) {
            return
        }
        val instructions = mutableListOf<Instruction>()
        val callLabel = Label(call.toString())
        val body = when (call) {
            Call.READ_INT, Call.READ_CHAR -> generateReadCall(call)
            Call.PRINT_INT -> generatePrintIntCall()
            Call.PRINT_BOOL -> generatePrintBoolCall()
            Call.PRINT_STRING -> generatePrintStringCall()
            Call.PRINT_REFERENCE -> generatePrintReferenceCall()
            Call.PRINT_LN -> generatePrintLnCall()
            Call.FREE_PAIR -> generateFreePairCall()
            Call.FREE_ARRAY -> generateFreeArrayCall()
            else -> throw NotImplementedError()
        }
        instructions.add(callLabel)
        instructions.addAll(body)
        LibraryCalls[call] = instructions
    }

fun translate(): List<Instruction> {
    val instructions = mutableListOf<Instruction>()
    for ((_, value) in LibraryCalls) {
        instructions.addAll(value)
    }
    return instructions
}

fun generateReadCall(call: Call): List<Instruction> {
    val stringFormat: String = when (call) {
        Call.READ_INT -> "%d" + 0.toChar()
        Call.READ_CHAR -> " %c" + 0.toChar()
        else -> throw Exception("Unable to generate code for non-read types")
    }
    val stringFormatLabel = CodeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R1)),
                LoadInstr(Register.R0, null, ImmediateLabel(stringFormatLabel), Condition.AL),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.SCANF.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
//    PUSH {lr}
//    MOV r1, r0
//    LDR r0, =stringFormatLabel
//    ADD r0, r0, #4
//    BL scanf
//    POP {pc}

    }

    fun generatePrintIntCall(): List<Instruction> {
        val stringFormat: String = "%d" + 0.toChar()
        val stringFormatLabel = CodeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R1)),
                LoadInstr(Register.R0, null, ImmediateLabel(stringFormatLabel), Condition.AL),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))

        // PUSH {lr}
        // MOV r1, r0
        // LDR r0, =stringFormatLabel
        // ADD r0, r0, #4
        // BL printf
        // MOV r0, #0
        // BL fflush
        // POP {pc}
    }

    fun generatePrintBoolCall(): List<Instruction> {
        val trueString = "true" + 0.toChar()
        val falseString = "false" + 0.toChar()
        val trueLabel = CodeGenerator.dataDirective.addStringLabel(trueString)
        val falseLabel = CodeGenerator.dataDirective.addStringLabel(falseString)

        val instructions = listOf(
                CompareInstr(Register.R0, null, 0),
                LoadInstr(Register.R0, null, ImmediateLabel(trueLabel), Condition.NE),
                LoadInstr(Register.R0, null, ImmediateLabel(falseLabel), Condition.EQ),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        // PUSH {lr}
        // CMP r0, #0
        // LDRNE r0, =msg_0
        // LDREQ r0, =msg_1
        // ADD r0, r0, #4
        // BL printf
        // MOV r0, #0
        // BL fflush
        // POP {pc}

    }


    fun generatePrintStringCall(): List<Instruction> {
        val stringFormat: String = "%.*s" + 0.toChar()
        val stringFormatLabel = CodeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                LoadInstr(Register.R0, null, RegisterAddr(Register.R1), Condition.AL),
                AddInstr(Condition.AL, Register.R2, Register.R0, ImmediateOperand(4)),
                LoadInstr(Register.R0, null, ImmediateLabel(stringFormatLabel), Condition.AL),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        // PUSH {lr}
        // LDR r1, [r0]
        // ADD r2, r0, #4
        // LDR r0, =stringFormatLabel
        // ADD r0, r0, #4
        // BL printf
        // MOV r0, #0
        // BL fflush
        // POP {pc}
    }


    fun generatePrintReferenceCall(): List<Instruction> {
        val stringFormat: String = "%d" + 0.toChar()
        val stringFormatLabel = CodeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R1)),
                LoadInstr(Register.R0, null, ImmediateLabel(stringFormatLabel), Condition.AL),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))

        // PUSH {lr}
        // MOV r1, r0
        // LDR r0, =stringFormatLabel
        // ADD r0, r0, #4
        // BL printf
        // MOV r0, #0
        // BL fflush
        // POP {pc}
    }

    fun generatePrintLnCall(): List<Instruction> {
        val stringFormat: String = "" + 0.toChar()
        val stringFormatLabel = CodeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                LoadInstr(Register.R0, null, ImmediateLabel(stringFormatLabel), Condition.AL),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PUTS.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        // PUSH {lr}
        // LDR r0, =msg_1
        // ADD r0, r0, #4
        // BL puts
        // MOV r0, #0
        // BL fflush
        // POP {pc}
    }

    fun generateFreePairCall(): List<Instruction> {
//    val errorLabel = codeGenerator.getDataSegment().addString(RuntimeErrors.RuntimeErrorType.NULL_REFERENCE.toString())
//    codeGenerator.getRuntimeErrors().addThrowRuntimeError()

        val instructions = listOf(
                CompareInstr(Register.R0, null, 0),
//            LoadInstr(Register.R0, null, ImmediateLabel(errorLabel), Condition.EQ),
                //BranchInstruction(Condition.EQ,  RuntimeErrors.throwRuntimeErrorLabel, false),
                PushInstr(Register.R0),
                LoadInstr(Register.R0, null, RegisterAddr(Register.R0), Condition.AL),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true),
                LoadInstr(Register.R0, null, RegisterAddr(Register.SP), Condition.AL),
                LoadInstr(Register.R0, null, RegisterAddrWithOffset(Register.R0, 4, true), Condition.AL),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true),
                PopInstr(Register.R0),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true),
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        // PUSH {lr}
        // CMP r0, #0
        // LDREQ r0, =msg_0
        // BEQ p_throw_runtime_error
        // PUSH {r0}
        // LDR r0, [r0]
        // BL free
        // LDR r0, [sp]
        // LDR r0, [r0, #4]
        // BL free
        // POP {r0}
        // BL free
        // POP {pc}
    }


    fun generateFreeArrayCall(): List<Instruction> {
//    val errorMessage = RuntimeErrors.ErrorType.NULL_REFERENCE.toString()
        val errorLabel = "TODO"
        //codeGenerator.getDataSegment().addString(errorMsg)
//    codeGenerator.runtimeErrors.addThrowRuntimeError()

        val instructions = listOf(
                CompareInstr(Register.R0, null, 0),
                LoadInstr(Register.R0, null, ImmediateLabel(errorLabel), Condition.EQ),
                //BranchInstruction(Condition.EQ,  RuntimeErrors.throwRuntimeErrorLabel, false),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        // PUSH {lr}
        // CMP r0, #0
        // LDREQ r0, =msg_0
        // BEQ p_throw_runtime_error
        // BL free
        // POP {pc}
    }
}