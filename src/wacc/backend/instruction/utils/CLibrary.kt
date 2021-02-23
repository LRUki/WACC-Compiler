package wacc.backend.instruction.utils

import wacc.backend.instruction.Instruction
import wacc.backend.instruction.instrs.FunctionLabel
import wacc.backend.instruction.instrs.Label

enum class LibraryFunctions {
    scanf,
    printf,
    fflush,
    malloc,
    free;

}
enum class Call {
    read_int,
    read_char,
    print_int,
    print_bool,
    print_string,
    print_reference,
    print_ln,
    free_array,
    free_pair;

    override fun toString(): String {
        return "p_${super.toString()}"
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
        Call.read_int, Call.read_char -> generateReadCall(call)
        Call.print_int -> generatePrintIntCall()
        Call.print_bool -> generatePrintBoolCall()
        Call.print_string -> generatePrintStringCall()
        Call.print_reference -> generatePrintReferenceCall()
        Call.print_ln -> generatePrintLnCall()
        Call.free_pair -> generateFreePairCall()
        Call.free_array -> generateFreeArrayCall()
        else -> throw NotImplementedError()
    }
    instructions.add(callLabel)
    instructions.addAll(body)
    LibraryCalls[call] = instructions
}

fun generateReadCall(call: Call): Collection<Instruction> {
//TODO
    return emptyList()
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
