package backend

import frontend.actionOnFiles
import org.junit.Before
import org.junit.Test
import wacc.WaccFile
import wacc.backend.CodeGenerator
import wacc.backend.generateCode
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.ImmediateIntMode
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.frontend.ast.program.ProgramAST
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TranslateTest {

    @Before
    @Throws(Exception::class)
    fun setUp() {
        CodeGenerator.reset()
    }

    private fun compileAndGenerate(file: File): List<Instruction> {
        val waccFile = WaccFile(file)
        val program = waccFile.parse(file.inputStream())
        waccFile.checkSyntax(program)
        val ast = waccFile.buildAST(program)
        waccFile.checkSemantics(ast)
        return generateCode(ast as ProgramAST)
    }

    @Test
    fun translateOfSkipFilesContainsDirectives() {
        val folder = File("wacc_examples/valid/basic/skip/")
        actionOnFiles(folder) { file ->
            val instrs = compileAndGenerate(file)
            assertTrue(instrs.contains(DirectiveInstr("text")))
            assertTrue(instrs.contains(DirectiveInstr("global main")))
            assertTrue(instrs.contains(DirectiveInstr("ltorg")))
        }
    }

    @Test
    fun translateOfFileWithNoStringsHasEmptyData() {
        val folder = File("wacc_examples/valid/basic/skip/")
        actionOnFiles(folder) { file ->
            val instrs = compileAndGenerate(file)
            assertFalse(instrs.contains(DirectiveInstr("data")))
            assertFalse(instrs.contains(DirectiveInstr("word")))
            assertFalse(instrs.contains(DirectiveInstr("ascii")))
            assertFalse(instrs.contains(Label("msg_")))
        }
    }

    @Test
    fun translateOfExitFilesContainsExitBranch() {
        val folder = File("wacc_examples/valid/basic/exit/")
        val actionOnFiles = actionOnFiles(folder) { file ->
            assertTrue(compileAndGenerate(file)
                    .contains(BranchInstr(Condition.AL, Label("exit"), true)))
        }
    }

    @Test
    fun translateOfStringDeclarationContainsStringDirective() {
        val folder = File("wacc_examples/valid/variables/stringDeclaration.wacc")
        val actionOnFiles = actionOnFiles(folder) { file ->
            assertTrue(compileAndGenerate(file)
                    .contains(DirectiveInstr("ascii \"Hello World!\"")))
        }
    }
}