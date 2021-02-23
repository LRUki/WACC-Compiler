package wacc.backend.instruction.utils

import wacc.backend.CodeGenerator
import wacc.backend.instruction.Instruction
//import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.*
import wacc.backend.instruction.instrs.*

enum class LibraryFunctions {
    SCANF,
    PRINTF,
    FFLUSH,
    MALLOC,
    FREE;
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

fun generateReadCall(call: Call): List<Instruction> {
    val stringFormat: String = when (call) {
        Call.READ_INT -> "%d" + 0.toChar()
        Call.READ_CHAR -> " %c" + 0.toChar()
        else -> throw Exception("Unable to generate code for non-read types")
    }
    val stringFormatLabel = CodeGenerator.stringLabels.add(stringFormat)

    val instructions = listOf(
            MoveInstr(Condition.AL, Register.R0, RegisterOperand(Register.R1)),
            LoadInstr(Register.R0, null, ImmediateLabel(stringFormatLabel), Condition.AL),
            AddInstr(Condition.AL, Register.R0, Register.R0, ImmediateOperand(4)),
            BranchInstr(Condition.AL, Label(LibraryFunctions.SCANF.toString()), null, true)
    )
    return listOf(PushInstr(listOf(Register.LR))) + instructions + listOf(PopInstr(listOf(Register.PC)))
//    PUSH {lr}
//    MOV r1, r0
//    LDR r0, =stringFormatLabel
//    ADD r0, r0, #4
//    BL scanf
//    POP {pc}

}

fun generatePrintIntCall(): Collection<Instruction> {
//TODO
    return emptyList()
}

fun generatePrintBoolCall(): Collection<Instruction> {
//TODO
    return emptyList()
}

fun generatePrintStringCall(): Collection<Instruction> {
//TODO
    return emptyList()
}

fun generatePrintReferenceCall(): Collection<Instruction> {
//TODO
    return emptyList()
}

fun generatePrintLnCall(): Collection<Instruction> {
//TODO
    return emptyList()
}

fun generateFreePairCall(): Collection<Instruction> {
//TODO
    return emptyList()
}

fun generateFreeArrayCall(): Collection<Instruction> {
//TODO
    return emptyList()
}
