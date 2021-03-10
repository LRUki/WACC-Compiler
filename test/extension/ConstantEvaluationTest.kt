package extension

import frontend.actionOnFiles
import wacc.backend.generateCode
import wacc.backend.translate.instruction.BranchInstr
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.Label
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.extension.optimization.ConstantEvaluationVisitor
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File
import java.util.function.IntBinaryOperator
import kotlin.test.Test
import kotlin.test.assertTrue

class ConstantEvaluationTest {
    @Test
    fun ArithmeticBinOpsAreOptimized() {
        val folder = File("wacc_examples/valid/expressions/")
        actionOnFiles(folder) { file ->
            if(file.name.contains("longExpr")){
                val ast = buildAst(file)
                val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
                assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
            }
        }
    }
    private fun buildAst(file: File): ProgramAST {
        val program = parse(file.inputStream())
        checkSyntax(program)
        val ast = buildAST(program)
        checkSemantics(ast)
        return ast as ProgramAST
    }
}