package wacc.frontend.ast.program

import wacc.backend.CodeGenerator.CLib
import wacc.backend.CodeGenerator.dataDirective
import wacc.backend.CodeGenerator.runtimeErrors
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.ImmediateIntMode
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
import wacc.backend.visitor.TranslateVisitor
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.AstVisitor
import wacc.frontend.ast.function.FuncAST
import wacc.frontend.ast.statement.StatAST

/**
 * AST node to represent a Program
 *
 * @property funcList List of Function ASTs defined in the program
 * @property stats List of all the statements in the program
 */
class ProgramAST(val funcList: List<FuncAST>, val stats: List<StatAST>) : AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        funcList.forEach { it.checkNameAndAddToST(table) }
        funcList.forEach { it.check(table) }
        stats.forEach {
            if (!it.check(table)) {
                return false
            }
        }
        return true
    }


    override fun <S : T, T> accept(visitor: AstVisitor<S>): T {
        return visitor.visitProgramAST(this)
    }

}