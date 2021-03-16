.data

.text
	.globl main
.LC0:
  .string "%d"
main:
  pushl %ebp
  movl %esp, %ebp
  subl $16, %esp
  movl $5, -4(%ebp)
  movl $10, -8(%ebp)
  movl -8(%ebp), %eax
  addl %eax, -4(%ebp)
  movl -4(%ebp), %eax
  movl %eax, %esi
  movl $.LC0, %edi
  movl $0, %eax
  call printf
  movl $0, %eax
  leave
  ret
