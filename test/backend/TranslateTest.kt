package backend

import frontend.actionOnFiles
import org.junit.Test
import wacc.backend.generateCode
import wacc.backend.instruction.instrs.DirectiveInstr
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File
import kotlin.test.assertTrue

class TranslateTest {

    @Test
    fun TranslateOfSkipFileContainsDirectives() {
        val folder = File("wacc_examples/valid/basic/skip/")
        actionOnFiles(folder) { file ->
            val program = parse(file.inputStream())
            checkSyntax(program)
            val ast = buildAST(program)
            checkSemantics(ast)
            val instr = generateCode(ast as ProgramAST)
            assertTrue(instr.contains(DirectiveInstr("text")))
            assertTrue(instr.contains(DirectiveInstr("global main")))
//            assertTrue(instr.contains(Label("main")))
            assertTrue(instr.contains(DirectiveInstr("ltorg")))
        }
    }

}