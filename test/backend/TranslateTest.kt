package backend

import frontend.actionOnFiles
import org.junit.Before
import org.junit.Test
import wacc.backend.CodeGenerator
import wacc.backend.generateCode
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.ImmediateIntMode
import wacc.backend.translate.instruction.instructionpart.ImmediateIntOperand
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
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
        val program = parse(file.inputStream())
        checkSyntax(program)
        val ast = buildAST(program)
        checkSemantics(ast)
        return generateCode(ast as ProgramAST)
    }

    @Test
    fun TranslateOfSkipFilesContainsDirectives() {
        val folder = File("wacc_examples/valid/basic/skip/")
        actionOnFiles(folder) { file ->
            val instrs = compileAndGenerate(file)
            assertTrue(instrs.contains(DirectiveInstr("text")))
            assertTrue(instrs.contains(DirectiveInstr("global main")))
            assertTrue(instrs.contains(DirectiveInstr("ltorg")))
        }
    }

    @Test
    fun TranslateOfFileWithNoStringsHasEmptyData() {
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
    fun TranslateOfExitFilesContainsExitBranch() {
        val folder = File("wacc_examples/valid/basic/exit/")
        val actionOnFiles = actionOnFiles(folder) { file ->
            compileAndGenerate(file)
                    .contains(BranchInstr(Condition.AL, Label("exit"), true))
        }
    }
//
//    @Test
//    fun TranslateOfScopedFilesContainsSOMETHIG() {
//        val file = File("wacc_examples/valid/expressions/intExpr1.wacc")
//        val program = compileAndGenerate(file)
//        assertTrue(program.contains(SubInstr(Condition.AL, Register.SP, Register.SP, ImmediateIntOperand(4))))
//    }


//    @Test
//    fun TranslateOfFilesWithFreeCallContainBranchToFree() {
//        val file = File("wacc_examples/valid/pairs/free.wacc")
//        val instrs = compileAndGenerate(file)
//        assertTrue(instrs.contains(BranchInstr(Condition.AL, Label("p_free_pair"), true)))
//    }

//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() { @Test
//    fun TranslateOfExitFilesContainsExitBranch() {
    /**
     * begin

     * print / ln
     * free
     * while
     * if
     * read
     * fst
     * snd
     * pair
     * newpair
     * all binops/ unops
     *
     */

}