package backend

import frontend.actionOnFiles
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import wacc.backend.generateCode
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.ImmediateInt
import wacc.backend.printCode
import wacc.buildAST
import wacc.checkSemantics
import wacc.checkSyntax
import wacc.frontend.ast.program.ProgramAST
import wacc.parse
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PrintCodeTest {

    @Test
    fun `can print code for skip files`() {
        var instrs = listOf(
                DirectiveInstr("text"),
                DirectiveInstr("global main"),
                Label("main"),
                PushInstr(Register.LR),
                LoadInstr(Register.R0, null, ImmediateInt(0), Condition.AL),
                PopInstr(Register.PC),
                DirectiveInstr("ltorg")
        )
        val output = printCode(instrs)
        assertThat(output, `is`("""
        .text
        .global main
        main:
        	PUSH {lr}
        	LDR r0, =0
        	POP {pc}
        	.ltorg
        
        """.trimIndent()))

    }

//    @Test
//    fun `can print code for exit files`() {
//        val folder = File("wacc_examples/valid/basic/exit/")
//        actionOnFiles(folder) { file ->
//            val program = parse(file.inputStream())
//            checkSyntax(program)
//            val ast = buildAST(program)
//            checkSemantics(ast)
//            val instrs = generateCode(ast as ProgramAST)
//            println(printCode(instrs))
//        }
//    }
}
