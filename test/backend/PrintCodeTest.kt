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

    @Test
    fun CompareInstrPrintsCode() {
        assertEquals("CMP r0, #1",
                CompareInstr(Register.R0, ImmediateIntOperand(1))
                        .toAssembly())

        assertEquals("CMP r0, #'a'",
                CompareInstr(Register.R0, ImmediateCharOperand('a'))
                        .toAssembly())

        assertEquals("CMP r0, r1, LSL #1",
                CompareInstr(Register.R0, RegShiftOffsetOperand(Register.R1,ShiftType.LSL, 1))
                        .toAssembly())
    }

    @Test
    fun ArithmeticInstrPrintsCode() {
        assertEquals("ADD r0, r1, #1",
                AddInstr(Condition.AL,Register.R0,Register.R1, ImmediateIntOperand(1),false)
                        .toAssembly())

        assertEquals("SUBSGE r0, r1, #1",
                SubInstr(Condition.GE ,Register.R0,Register.R1, ImmediateIntOperand(1),true)
                        .toAssembly())

        assertEquals("SMULL r0, r1, r2, r3",
                MultInstr(Condition.AL ,Register.R0,Register.R1, Register.R2,Register.R3,false)
                        .toAssembly())
    }
}
