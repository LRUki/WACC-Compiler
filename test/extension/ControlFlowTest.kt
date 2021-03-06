package extension

import frontend.actionOnFiles
import org.junit.Test
import wacc.backend.generateCode
import wacc.extension.optimisation.ConstantEvaluationVisitor
import wacc.extension.optimisation.ControlFlowVisitor
import wacc.frontend.ast.AST
import wacc.frontend.ast.program.ProgramAST
import wacc.WaccFile
import java.io.File
import kotlin.test.assertTrue

class ControlFlowTest {
    val path = "ci/extension_wacc/valid/optimisation/control_flow"

    private fun simpleFrontend(file: File): AST {
        val waccFile = WaccFile(file)
        val program = waccFile.parse(file.inputStream())
        waccFile.checkSyntax(program)

        val ast = waccFile.buildAST(program)
        waccFile.checkSemantics(ast)
        return ast
    }

    @Test
    fun ifStatWithFalseCondIsOptimised() {
        val folder = File("$path/if/ifFalse")
        actionOnFiles(folder) { file ->
            val ast = simpleFrontend(file)
            val optimizedAst = ControlFlowVisitor().visit(ast)

            val instrs = generateCode(ast as ProgramAST)
            val optimisedInstrs = generateCode(optimizedAst as ProgramAST)

            assertTrue { instrs.size >= optimisedInstrs.size }
        }
    }

    @Test
    fun ifStatWithTrueCondIsOptimised() {
        val folder = File("$path/if/ifTrue")
        actionOnFiles(folder) { file ->
            val ast = simpleFrontend(file)
            val optimizedAst = ControlFlowVisitor().visit(ast)

            val instrs = generateCode(ast as ProgramAST)
            val optimisedInstrs = generateCode(optimizedAst as ProgramAST)

            assertTrue { instrs.size >= optimisedInstrs.size }
        }
    }

    @Test
    fun whileTrueStaysTheSame() {
        val folder = File("$path/while/whileTrue")
        actionOnFiles(folder) { file ->
            val ast = simpleFrontend(file)
            val optimizedAst = ControlFlowVisitor().visit(ast)

            val instrs = generateCode(ast as ProgramAST)
            val optimisedInstrs = generateCode(optimizedAst as ProgramAST)

            assertTrue { instrs.size == optimisedInstrs.size }
        }
    }

    @Test
    fun whileFalseRemovesLoopBody() {
        val folder = File("$path/while/whileFalse")
        actionOnFiles(folder) { file ->
            val ast = simpleFrontend(file)
            val optimizedAst = ControlFlowVisitor().visit(ast)

            val instrs = generateCode(ast as ProgramAST)
            val optimisedInstrs = generateCode(optimizedAst as ProgramAST)

            assertTrue { instrs.size > optimisedInstrs.size }
        }
    }

    @Test
    fun ifControlFlowWorksWithConstantEval() {

        val file = File("$path/if/constantEvalIf.wacc")

        val ast = simpleFrontend(file)
        val optimizedAst = ConstantEvaluationVisitor().visit(ast)
        val evenMoreOptimizedAst = ControlFlowVisitor().visit(optimizedAst)

        val instrs = generateCode(ast as ProgramAST)
        val optimisedInstrs = generateCode(evenMoreOptimizedAst as ProgramAST)

        assertTrue { instrs.size > optimisedInstrs.size }
    }

    @Test
    fun whileControlFlowWorksWithConstantEval() {

        val file = File("$path/while/constantEvalWhile.wacc")

        val ast = simpleFrontend(file)
        val optimizedAst = ConstantEvaluationVisitor().visit(ast)
        val evenMoreOptimizedAst = ControlFlowVisitor().visit(optimizedAst)

        val instrs = generateCode(ast as ProgramAST)
        val optimisedInstrs = generateCode(evenMoreOptimizedAst as ProgramAST)

        assertTrue { instrs.size > optimisedInstrs.size }
    }

}


//maintains integrity


