package backend

import frontend.actionOnFiles
import org.junit.Test
import wacc.backend.generateCode
import wacc.backend.translate.RuntimeErrors
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File

class RuntimeErrorsTest {
    val path = "wacc_examples/valid/runtimeErr"

    @Test
    fun arrayRuntimeErrorContainsCheckArrayBounds() {
        val folder = File("${path}/arrayOutOfBounds")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            generateCode(ast as ProgramAST).contains(RuntimeErrors.checkArrayBoundsLabel)
        }
    }

    @Test
    fun divideByZeroRuntimeErrorContainsCheckDivideByZero() {
        val folder = File("${path}/divideByZero")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            generateCode(ast as ProgramAST).contains(RuntimeErrors.divideZeroCheckLabel)
        }
    }

    @Test
    fun integerOverflowRuntimeErrorContainsThrowIntegerOverflow() {
        val folder = File("${path}/integerOverflow")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            generateCode(ast as ProgramAST).contains(RuntimeErrors.throwOverflowErrorLabel)
        }
    }

    @Test
    fun nullDereferenceRuntimeErrorContainsCheckNullPointer() {
        val folder = File("${path}/nullDereference")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            generateCode(ast as ProgramAST).contains(RuntimeErrors.nullReferenceLabel)
        }
    }

}