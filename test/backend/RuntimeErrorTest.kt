package backend

import frontend.actionOnFiles
import org.junit.Test
import wacc.backend.generateCode
import wacc.backend.translate.RuntimeError
import wacc.backend.translate.instruction.DirectiveInstr
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File
import kotlin.test.assertTrue

class RuntimeErrorTest {
    val path = "wacc_examples/valid/runtimeErr/"
    @Test
    fun runtimeErrorContainsThrowRuntimeErrorLabel() {
        val folder = File(path)
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            val instrs = generateCode(ast as ProgramAST)
            println(file.path)
            assertTrue(instrs.contains(RuntimeError.throwOverflowErrorLabel))
        }
    }

    @Test
    fun arrayRuntimeErrorContainsCheckArrayBounds() {
        val folder = File("${path}arrayOutOfBounds")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            generateCode(ast as ProgramAST).contains(RuntimeError.checkArrayBoundsLabel)
        }
    }
}