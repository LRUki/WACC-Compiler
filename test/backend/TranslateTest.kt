package backend

import frontend.actionOnFiles
import org.junit.Test
import wacc.backend.generateCode
import wacc.backend.translate.instruction.DirectiveInstr
import wacc.backend.translate.instruction.Label
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TranslateTest {

    @Test
    fun TranslateOfSkipFilesContainsDirectives() {
        val folder = File("wacc_examples/valid/basic/skip/")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            val instrs = generateCode(ast as ProgramAST)
//            assertTrue(instrs.contains(DirectiveInstr("data")))
            assertTrue(instrs.contains(DirectiveInstr("text")))
            assertTrue(instrs.contains(DirectiveInstr("global main")))
//            assertTrue(instrs.contains(Label("main")))
            assertTrue(instrs.contains(DirectiveInstr("ltorg")))
        }
    }

    @Test
    fun TranslateOfFileWithNoStringsHasEmptyData() {
        val folder = File("wacc_examples/valid/basic/skip/")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            val instrs = generateCode(ast as ProgramAST)
            assertFalse(instrs.contains(DirectiveInstr("data")))
            assertFalse(instrs.contains(DirectiveInstr("word")))
            assertFalse(instrs.contains(DirectiveInstr("ascii")))
            assertFalse(instrs.contains(Label("msg_")))
        }
    }

}