package wacc.backend.translate.instruction.instructionpart

/**
 * An enum for memory types that can be associated with an instruction
 */
enum class MemoryType {
    B,  // unsigned Byte
    BT, // byte with User mode privilege
    SB, // signed Byte
    H,  // unsigned Halfword
    SH, // signed Halfword
    D   // doubleword
}