package backend

import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import wacc.backend.translate.instruction.*
import wacc.backend.printCode
import wacc.backend.translate.instruction.instructionpart.*

class PrintCodeTest {

    @Test
    fun DirectiveInstrPrintsCodeWithIndentation() {
        val instrs = listOf(
                DirectiveInstr("text"),
                DirectiveInstr("global main"),
                DirectiveInstr("ltorg")
        )
        val output = printCode(instrs)
        assertThat(output, `is`("""
        .text
        .global main
        	.ltorg
        
        """.trimIndent()))

    }

    @Test
    fun BranchInstrPrintsCode() {
        assertEquals("B label",
                BranchInstr(Condition.AL,Label("label"),false)
                        .toAssembly())

        assertEquals("BL label",
                BranchInstr(Condition.AL,Label("label"),true)
                        .toAssembly())
        assertEquals("BLEQ label",
                BranchInstr(Condition.EQ,Label("label"),true)
                        .toAssembly())
    }


}
