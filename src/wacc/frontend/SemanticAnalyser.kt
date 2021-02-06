package wacc.frontend

import wacc.frontend.ast.*
import wacc.frontend.identifiers.*

class SemanticAnalyser {

    fun getTypeString(typeAST: TypeAST) : String {
        return when(typeAST) {
                is BaseTypeAST  ->  typeAST.type.name.toLowerCase()
                is ArrayTypeAST ->  getTypeString(typeAST.type)
                is PairTypeAST  ->  "pair"
                else            ->  throw RuntimeException()
            }
    }

    fun analyse() {
        print("TODO")
    }



}