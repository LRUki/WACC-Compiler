.data

msg_0:
	.word 7
	.ascii "Correct"
msg_1:
	.word 5
	.ascii "%.*s\0"
msg_2:
	.word 1
	.ascii "\0"
msg_3:
	.word 5
	.ascii "Wrong"

.text

.global main
main:
	PUSH {lr}
	SUB sp, sp, #1
	MOV r4, #1
	STRB r4, [sp]
	LDRSB r4, [sp]
	MOV r5, #1
	CMP r4, r5
	MOVEQ r4, #1
	MOVNE r4, #0
	CMP r4, #0
	BEQ L0
	LDR r4, =msg_0
	MOV r0, r4
	BL p_print_string
	BL p_print_ln
	B L1
L0:
	LDR r4, =msg_3
	MOV r0, r4
	BL p_print_string
	BL p_print_ln
L1:
	ADD sp, sp, #1
	LDR r0, =0
	POP {pc}
	.ltorg
p_print_string:
	PUSH {lr}
	LDR r1, [r0]
	ADD r2, r0, #4
	LDR r0, =msg_1
	ADD r0, r0, #4
	BL printf
	MOV r0, #0
	BL fflush
	POP {pc}
p_print_ln:
	PUSH {lr}
	LDR r0, =msg_2
	ADD r0, r0, #4
	BL puts
	MOV r0, #0
	BL fflush
	POP {pc}
