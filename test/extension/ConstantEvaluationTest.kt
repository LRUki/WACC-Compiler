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
    fun IntBinOpsAreOptimized() {
        val folder = File("extension_wacc/valid/optimization/const_eval/binary_operator")
        actionOnFiles(folder) { file ->
            if(file.name.contains("int")){
                val ast = buildAst(file)
                val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
                assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
            }
        }
    }

    @Test
    fun BoolBinOpsAreOptimized() {
        val folder = File("extension_wacc/valid/optimization/const_eval/binary_operator")
        actionOnFiles(folder) { file ->
            if(file.name.contains("bool")) {
                val ast = buildAst(file)
                val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
                assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
            }
        }
    }

    @Test
    fun CmpBinOpsAreOptimized() {
        val folder = File("extension_wacc/valid/optimization/const_eval/binary_operator")
        actionOnFiles(folder) { file ->
            if(file.name.contains("cmp")) {
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