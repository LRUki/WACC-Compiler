package extension

import frontend.actionOnFiles
import wacc.backend.generateCode
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
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
    val path = "extension_wacc/valid/optimization/const_eval"

    @Test
    fun intBinOpsAreOptimized() {
        val folder = File("$path/binary_operator")
        actionOnFiles(folder) { file ->
            if (file.name.contains("int")) {
                val ast = buildAst(file)
                val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
                assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
            }
        }
    }

    @Test
    fun boolBinOpsAreOptimized() {
        val folder = File("$path/binary_operator")
        actionOnFiles(folder) { file ->
            if (file.name.contains("bool")) {
                val ast = buildAst(file)
                val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
                assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
            }
        }
    }

    @Test
    fun cmpBinOpsAreOptimized() {
        val folder = File("$path/binary_operator")
        actionOnFiles(folder) { file ->
            if (file.name.contains("cmp")) {
                val ast = buildAst(file)
                val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
                assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
            }
        }
    }

    @Test
    fun negationUnOpsAreOptimized() {
        val ast = buildAst(
                File("$path/unary_operator/negationExpr.wacc"))
        val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
        assertTrue { generateCode(ast).size > generateCode(optimizedAst).size }
    }

    @Test
    fun ordUnOpsAreOptimized() {

        val ast = buildAst(
                File("$path/unary_operator/ordExpr.wacc"))
        val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
        var chrEvaluated = false
        generateCode(optimizedAst).forEach {
            if (it.toArm() == LoadInstr(Condition.AL,
                            null,
                            ImmediateIntMode(97),
                            Register.R4).toArm()) {
                chrEvaluated = true
            }
        }
        assertTrue(chrEvaluated)
    }

    @Test
    fun chrUnOpsAreOptimized() {

        val ast = buildAst(
                File("$path/unary_operator/chrExpr.wacc"))
        val optimizedAst = ConstantEvaluationVisitor().visit(ast) as ProgramAST
        var ordEvaluated = false
        generateCode(optimizedAst).forEach {
            if (it.toArm() == MoveInstr(Condition.AL,
                            Register.R4,
                            ImmediateCharOperand('a')).toArm()) {
                ordEvaluated = true
            }
        }
        assertTrue(ordEvaluated)
    }

    private fun buildAst(file: File): ProgramAST {
        val program = parse(file.inputStream())
        checkSyntax(program)
        val ast = buildAST(program)
        checkSemantics(ast)
        return ast as ProgramAST
    }
}