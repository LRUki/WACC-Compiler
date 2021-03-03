package wacc.backend.translate.instr

interface Instr {
    fun toAssembly(): String
}