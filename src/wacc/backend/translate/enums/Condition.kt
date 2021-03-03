package wacc.backend.translate.enums

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

    fun toAssembly(): String {
        return if (this == Condition.AL) "" else this.name
    }
}

