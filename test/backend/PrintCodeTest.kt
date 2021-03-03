package backend

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.ImmediateIntMode
import wacc.backend.printCode

class PrintCodeTest {

    @Test
    fun `can print code for skip files`() {
        val instrs = listOf(
                DirectiveInstr("text"),
                DirectiveInstr("global main"),
                Label("main"),
                PushInstr(Register.LR),
                LoadInstr(Condition.AL, null, ImmediateIntMode(0), Register.R0),
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
