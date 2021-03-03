package wacc.backend.translate.instruction.instructionpart

enum class MemoryType {
    B,  // unsigned Byte
    BT, // byte with User mode privilege
    SB, // signed Byte
    H,  // unsigned Halfword
    SH, // signed Halfword
    D   // doubleword
}