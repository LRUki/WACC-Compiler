package extension

import frontend.actionOnFiles
import org.junit.Test
import wacc.backend.generateCode
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.extension.optimization.ControlFlowVisitor
import wacc.frontend.ast.AST
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File
import kotlin.test.assertTrue

class ControlFlowTest {
    val path = "extension_wacc/valid/optimization/control_flow"

    private fun simpleFrontend(file: File): AST {
        val program = parse(file.inputStream())
        checkSyntax(program)

        val ast = buildAST(program)
        checkSemantics(ast)
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
}

//if+whiles

//maintains integrity


//control flow + constant eval.