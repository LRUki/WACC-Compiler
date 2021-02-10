package wacc.frontend

import org.antlr.v4.runtime.ParserRuleContext
import wacc.frontend.ast.*
import wacc.frontend.ast.array.ArrayElemAST
import wacc.frontend.ast.assign.CallRhsAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.expression.*
import wacc.frontend.exception.SemanticException

class SemanticAnalyser {
    companion object {
        val defCharTypeAST = BaseTypeAST(BaseType.CHAR)
        val defIntTypeAST = BaseTypeAST(BaseType.INT)
        val defBoolTypeAST = BaseTypeAST(BaseType.BOOL)
        val defStringTypeAST = BaseTypeAST(BaseType.STRING)
        val defAnyTypeAST = BaseTypeAST(BaseType.ANY)
        val defArrayTypeAST = ArrayTypeAST(defAnyTypeAST, 1)
    }
}
