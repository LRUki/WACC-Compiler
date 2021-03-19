package backend

import frontend.actionOnFiles
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import wacc.WaccFile
import wacc.backend.CodeGenerator
import wacc.backend.generateCode
import wacc.backend.translate.RuntimeErrors
import wacc.frontend.ast.program.ProgramAST
import java.io.File

class RuntimeErrorTest {
    val PATH = "ci/wacc_examples/valid/runtimeErr"

//    @Before
//    @Throws(Exception::class)
//    fun setUp() {
//        CodeGenerator.reset()
//    }

    @Test
    fun arrayRuntimeErrorContainsCheckArrayBounds() {
        val folder = File("${PATH}/arrayOutOfBounds")
        actionOnFiles(folder) { file ->
            val waccFile = WaccFile(file)
            val program = waccFile.parse(file.inputStream())
            waccFile.checkSyntax(program)
            val ast = waccFile.buildAST(program)
            waccFile.checkSemantics(ast)
            assertTrue(generateCode(ast as ProgramAST).contains(RuntimeErrors.checkArrayBoundsLabel))
        }
    }

    @Test
    fun divideByZeroRuntimeErrorContainsCheckDivideByZero() {
        val folder = File("${PATH}/divideByZero")
        actionOnFiles(folder) { file ->
            val waccFile = WaccFile(file)
            val program = waccFile.parse(file.inputStream())
            waccFile.checkSyntax(program)
            val ast = waccFile.buildAST(program)
            waccFile.checkSemantics(ast)
            assertTrue(generateCode(ast as ProgramAST).contains(RuntimeErrors.divideZeroCheckLabel))
        }
    }

    @Test
    fun integerOverflowRuntimeErrorContainsThrowIntegerOverflow() {
        val folder = File("${PATH}/integerOverflow")
        actionOnFiles(folder) { file ->
            val waccFile = WaccFile(file)
            val program = waccFile.parse(file.inputStream())
            waccFile.checkSyntax(program)
            val ast = waccFile.buildAST(program)
            waccFile.checkSemantics(ast)
            assertTrue(generateCode(ast as ProgramAST).contains(RuntimeErrors.throwOverflowErrorLabel))
        }
    }

    @Test
    fun nullDereferenceRuntimeErrorContainsCheckNullPointer() {
        val folder = File("${PATH}/nullDereference")
        actionOnFiles(folder) { file ->
            val waccFile = WaccFile(file)
            val program = waccFile.parse(file.inputStream())
            waccFile.checkSyntax(program)
            val ast = waccFile.buildAST(program)
            waccFile.checkSemantics(ast)
            assertTrue(generateCode(ast as ProgramAST).contains(RuntimeErrors.throwRuntimeErrorLabel))
        }
    }

}