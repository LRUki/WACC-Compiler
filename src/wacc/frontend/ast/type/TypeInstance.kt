package wacc.frontend.ast.type

/**
 * Type Instances for TypeASTs to concrete types
 */
object TypeInstance {
    val charTypeInstance = BaseTypeAST(BaseType.CHAR)
    val intTypeInstance = BaseTypeAST(BaseType.INT)
    val boolTypeInstance = BaseTypeAST(BaseType.BOOL)
    val stringTypeInstance = BaseTypeAST(BaseType.STRING)
}
