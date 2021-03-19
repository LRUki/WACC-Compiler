package extension

import frontend.actionOnFiles
import wacc.WaccFile
import wacc.backend.generateCode
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
import wacc.extension.optimisation.ConstantEvaluationVisitor
import wacc.frontend.ast.program.ProgramAST
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class ConstantEvaluationTest {
    val path = "extension_wacc/valid/optimisation/const_eval"

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
        val waccFile = WaccFile(file)
        val program = waccFile.parse(file.inputStream())
        waccFile.checkSyntax(program)
        val ast = waccFile.buildAST(program)
        waccFile.checkSemantics(ast)
        return ast as ProgramAST
    }
}