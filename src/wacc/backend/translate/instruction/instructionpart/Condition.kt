package wacc.backend.translate.instruction.instructionpart

/**
* An enum for conditions that can be associated with an instruction
*/
enum class Condition {

    EQ,   // Equal
    NE,   // Not equal
    CS,   // Higher or same (unsigned >=)
    CC,   // Lower (unsigned <)
    MI,   // Negative
    PL,   // Positive or zero
    VS,   // Overflow
    VC,   // No overflow
    HI,   // Higher (unsigned <=)
    LS,   // Lower or same (unsigned >=)
    GE,   // Signed >=
    LT,   // Signed <
    GT,   // Signed >
    LE,   // Signed <=
    AL;    // Always

    // The always condition should not be printed
    fun toArm(): String {
        return if (this == AL) "" else name
    }

    fun toX86(): String {
        return when(this) {
            EQ -> "e"
            NE -> "ne"
            CS -> "ae"
            CC -> "b"
            MI -> "TODO"
            PL -> "TODO"
            VS -> "o"
            VC -> "no"
            HI -> "a"
            LS -> "be"
            GE -> "ge"
            LT -> "l"
            GT -> "g"
            LE -> "le"
            AL -> ""
        }
    }
}

