package wacc.backend.translate

import wacc.backend.CodeGenerator
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.RuntimeErrors.Companion.throwRuntimeErrorLabel
import wacc.backend.translate.instruction.instructionpart.*

/**
 * C library class represents everything that requires the C Library
 * Generates the C library functions assembly code
 *
 */
class CLibrary(val codeGenerator: CodeGenerator) {
    private val pointerOffset = 4

    /**
     * Library Functions are those that are branched to directly
     */
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

    /**
     * Calls are blocks of code that branch to Library functions
     */
    enum class Call {
        READ_INT,
        READ_CHAR,
        PRINT_INT,
        PRINT_BOOL,
        PRINT_STRING,
        PRINT_REFERENCE,
        PRINT_LN,
        FREE_ARRAY,
        FREE_PAIR,
        FREE_STRUCT;

        override fun toString(): String {
            return "p_${super.toString().toLowerCase()}"
        }
    }

    /** Creates an empty hashmap between Calls and their respective blocks of code */
    private val libraryCalls: HashMap<Call, List<Instruction>> = LinkedHashMap()

    /** Given a call, we add it and it's list of instructions
     to the hash map (provided it isn't already in the map)*/
    @Synchronized
    fun addCode(call: Call) {
        if (libraryCalls.containsKey(call)) {
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
            Call.FREE_STRUCT -> generateFreeStructCall()
        }
        instructions.add(callLabel)
        instructions.addAll(body)
        libraryCalls[call] = instructions
    }


    /** Adds all the individual blocks of code in the hash map together and returns the result.*/
    fun translate(): List<Instruction> {
        val instructions = mutableListOf<Instruction>()
        for ((_, value) in libraryCalls) {
            instructions.addAll(value)
        }
        return instructions
    }

    /** Generates the block of code for read call */
    fun generateReadCall(call: Call): List<Instruction> {
        val stringFormat: String = when (call) {
            Call.READ_INT -> "%d\\0"
            Call.READ_CHAR -> " %c\\0"
            else -> throw Exception("Unable to generate code for non-read types")
        }
        val stringFormatLabel = codeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                MoveInstr(Condition.AL, Register.R1, RegisterOperand(Register.R0)),
                LoadInstr(Condition.AL, null, ImmediateLabelMode(stringFormatLabel), Register.R0),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateIntOperand(pointerOffset)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.SCANF.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * MOV r1, r0
         * LDR r0, =stringFormatLabel
         * ADD r0, r0, #4
         * BL scanf
         * POP {pc}
         */

    }

    /** Generates the block of code for print int call */
    fun generatePrintIntCall(): List<Instruction> {
        val stringFormat = "%d\\0"
        val stringFormatLabel = codeGenerator.dataDirective.addStringLabel(stringFormat)
        val instructions = listOf(
                MoveInstr(Condition.AL, Register.R1, RegisterOperand(Register.R0)),
                LoadInstr(Condition.AL, null, ImmediateLabelMode(stringFormatLabel), Register.R0),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateIntOperand(pointerOffset)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateIntOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))

        /**
         * PUSH {lr}
         * MOV r1, r0
         * LDR r0, =stringFormatLabel
         * ADD r0, r0, #4
         * BL printf
         * MOV r0, #0
         * BL fflush
         * POP {pc}
         */
    }

    /** Generates the block of code for print bool call */
    fun generatePrintBoolCall(): List<Instruction> {
        val trueString = "true\\0"
        val falseString = "false\\0"
        val trueLabel = codeGenerator.dataDirective.addStringLabel(trueString)
        val falseLabel = codeGenerator.dataDirective.addStringLabel(falseString)

        val instructions = listOf(
                CompareInstr(Register.R0, ImmediateIntOperand(0)),
                LoadInstr(Condition.NE, null, ImmediateLabelMode(trueLabel), Register.R0),
                LoadInstr(Condition.EQ, null, ImmediateLabelMode(falseLabel), Register.R0),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateIntOperand(pointerOffset)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateIntOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * CMP r0, #0
         * LDRNE r0, =msg_0
         * LDREQ r0, =msg_1
         * ADD r0, r0, #4
         * BL printf
         * MOV r0, #0
         * BL fflush
         * POP {pc}
         */
    }

    /** Generates the block of code for print string call */
    fun generatePrintStringCall(): List<Instruction> {
        val stringFormat = "%.*s\\0"
        val stringFormatLabel = codeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                LoadInstr(Condition.AL, null, RegisterMode(Register.R0), Register.R1),
                AddInstr(Condition.AL, Register.R2, Register.R0, ImmediateIntOperand(pointerOffset)),
                LoadInstr(Condition.AL, null, ImmediateLabelMode(stringFormatLabel), Register.R0),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateIntOperand(pointerOffset)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateIntOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * LDR r1, [r0]
         * ADD r2, r0, #4
         * LDR r0, =stringFormatLabel
         * ADD r0, r0, #4
         * BL printf
         * MOV r0, #0
         * BL fflush
         * POP {pc}
          */
    }

    /** Generates the block of code for print reference call */
    fun generatePrintReferenceCall(): List<Instruction> {
        val stringFormat = "%p\\0"
        val stringFormatLabel = codeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                MoveInstr(Condition.AL, Register.R1, RegisterOperand(Register.R0)),
                LoadInstr(Condition.AL, null, ImmediateLabelMode(stringFormatLabel), Register.R0),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateIntOperand(pointerOffset)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PRINTF.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateIntOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * MOV r1, r0
         * LDR r0, =stringFormatLabel
         * ADD r0, r0, #4
         * BL printf
         * MOV r0, #0
         * BL fflush
         * POP {pc}
         */
    }

    /** Generates the block of code for print new line call */
    fun generatePrintLnCall(): List<Instruction> {
        val stringFormat = "\\0"
        val stringFormatLabel = codeGenerator.dataDirective.addStringLabel(stringFormat)

        val instructions = listOf(
                LoadInstr(Condition.AL, null, ImmediateLabelMode(stringFormatLabel), Register.R0),
                AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateIntOperand(pointerOffset)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.PUTS.toString()), true),
                MoveInstr(Condition.AL, Register.R0, ImmediateIntOperand(0)),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FFLUSH.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * LDR r0, =msg_1
         * ADD r0, r0, #4
         * BL puts
         * MOV r0, #0
         * BL fflush
         * POP {pc}
         */
    }

    /** Generates the block of code for free pair call */
    fun generateFreePairCall(): List<Instruction> {
        val label = codeGenerator.dataDirective.addStringLabel(RuntimeErrors.ErrorType.NULL_REFERENCE.toString())

        val instructions = listOf(
                CompareInstr(Register.R0, ImmediateIntOperand(0)),
                LoadInstr(Condition.EQ, null, ImmediateLabelMode(label), Register.R0),
                BranchInstr(Condition.EQ, throwRuntimeErrorLabel, false),
                PushInstr(Register.R0),
                LoadInstr(Condition.AL, null, RegisterMode(Register.R0), Register.R0),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true),
                LoadInstr(Condition.AL, null, RegisterMode(Register.SP), Register.R0),
                LoadInstr(Condition.AL, null, RegisterAddrWithOffsetMode(Register.R0, pointerOffset, false), Register.R0),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true),
                PopInstr(Register.R0),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true),
        )
        codeGenerator.runtimeErrors.addThrowRuntimeError()
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * CMP r0, #0
         * LDREQ r0, =msg_0
         * BEQ p_throw_runtime_error
         * PUSH {r0}
         * LDR r0, [r0]
         * BL free
         * LDR r0, [sp]
         * LDR r0, [r0, #4]
         * BL free
         * POP {r0}
         * BL free
         * POP {pc}
         */
    }

    /** Generates the code for freeing a single malloced object */
    private fun freeSingleMallocedObject(): List<Instruction> {
        val errorMessage = RuntimeErrors.ErrorType.NULL_REFERENCE.toString()
        val errorLabel = codeGenerator.dataDirective.addStringLabel(errorMessage)

        val instructions = listOf(
                CompareInstr(Register.R0, ImmediateIntOperand(0)),
                LoadInstr(Condition.EQ, null, ImmediateLabelMode(errorLabel), Register.R0),
                BranchInstr(Condition.AL, Label(LibraryFunctions.FREE.toString()), true)
        )
        return listOf(PushInstr(Register.LR)) + instructions + listOf(PopInstr(Register.PC))
        /**
         * PUSH {lr}
         * CMP r0, #0
         * LDREQ r0, =msg_0
         * BEQ p_throw_runtime_error
         * BL free
         * POP {pc}
         */
    }

    /** Generates the block of code for free array call */
    fun generateFreeArrayCall(): List<Instruction> {
        return freeSingleMallocedObject()
    }

    /** Generates the block of code for freeing a struct */
    fun generateFreeStructCall(): List<Instruction> {
        return freeSingleMallocedObject()
    }

}
