package wacc.frontend.identifierObjs

interface Statement {
    fun getStatType():StatType

}

enum class StatType {
    SKIP,
    ASSIGN,
    READ,
    FREE,
    RETURN,
    EXIT,
    PRINT,
    PRINTLN,
    IF,
    WHILE,
    STAT_BLOCK
}