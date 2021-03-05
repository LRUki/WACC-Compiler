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
    fun directiveInstrPrintsCode() {

        assertEquals(".text",
                DirectiveInstr("text").toArm())
        assertEquals(".word",
                DirectiveInstr("word").toArm())
    }

    @Test
    fun branchInstrPrintsCode() {
        assertEquals("B label",
                BranchInstr(Condition.AL,Label("label"),false)
                        .toArm())

        assertEquals("BL label",
                BranchInstr(Condition.AL,Label("label"),true)
                        .toArm())
        assertEquals("BLEQ label",
                BranchInstr(Condition.EQ,Label("label"),true)
                        .toArm())
    }

    @Test
    fun compareInstrPrintsCode() {
        assertEquals("CMP r0, #1",
                CompareInstr(Register.R0, ImmediateIntOperand(1))
                        .toArm())

        assertEquals("CMP r0, #'a'",
                CompareInstr(Register.R0, ImmediateCharOperand('a'))
                        .toArm())

        assertEquals("CMP r0, r1, LSL #1",
                CompareInstr(Register.R0, RegShiftOffsetOperand(Register.R1,ShiftType.LSL, 1))
                        .toArm())
    }

    @Test
    fun arithmeticInstrPrintsCode() {
        assertEquals("ADD r0, r1, #1",
                AddInstr(Condition.AL,Register.R0,Register.R1, ImmediateIntOperand(1),false)
                        .toArm())

        assertEquals("SUBSGE r0, r1, #1",
                SubInstr(Condition.GE ,Register.R0,Register.R1, ImmediateIntOperand(1),true)
                        .toArm())

        assertEquals("SMULL r0, r1, r2, r3",
                MultInstr(Condition.AL ,Register.R0,Register.R1, Register.R2,Register.R3,false)
                        .toArm())
    }

    @Test
    fun loadInstrPrintsCode() {
        assertEquals("LDR r0, =1",
                LoadInstr(Condition.AL,null,ImmediateIntMode(1), Register.R0)
                        .toArm())

        assertEquals("LDRB r0, =1",
                LoadInstr(Condition.AL,MemoryType.B,ImmediateIntMode(1), Register.R0)
                        .toArm())

        assertEquals("LDREQ r0, [r0, #3]!",
                LoadInstr(Condition.EQ,null,RegisterAddrWithOffsetMode(Register.R0 ,3,true), Register.R0)
                        .toArm())
    }

    @Test
    fun logicInstrPrintsCode() {
        assertEquals("AND r0, r1, #1",
                AndInstrType(Condition.AL,Register.R0,Register.R1,ImmediateIntOperand(1))
                        .toArm())

        assertEquals("EORSEQ r0, r1, #1",
                XorInstrType(Condition.EQ,Register.R0,Register.R1,ImmediateIntOperand(1), true)
                        .toArm())


        assertEquals("ORRS r0, r1, r2",
                OrInstrType(Condition.AL,Register.R0,Register.R1,RegisterOperand(Register.R2), true)
                        .toArm())
    }

    @Test
    fun moveInstrPrintsCode() {
        assertEquals("MOV r0, #1",
                MoveInstr(Condition.AL,Register.R0,ImmediateIntOperand(1))
                        .toArm())

        assertEquals("MOVEQ r0, #1",
                MoveInstr(Condition.EQ,Register.R0,ImmediateIntOperand(1))
                        .toArm())

    }

    @Test
    fun pushAndPopInstrPrintsCode() {
        assertEquals("PUSH {r0}",
                PushInstr(Register.R0)
                        .toArm())

        assertEquals("POP {r0}",
                PopInstr(Register.R0)
                        .toArm())

    }

    @Test
    fun storeInstrPrintsCode() {
        assertEquals("STR r0, =1",
                StoreInstr(null, ImmediateIntMode(1),Register.R0)
                        .toArm())

        assertEquals("STRB r0, [r1, #4]",
                StoreInstr(MemoryType.B, RegisterAddrWithOffsetMode(Register.R1,4,false),Register.R0)
                        .toArm())

    }

    @Test
    fun printCodeIndentsMainCode() {
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
}
