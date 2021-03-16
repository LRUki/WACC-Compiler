.data
.globl hello
hello:
.string "Hi World\n"

.text
.globl main
.LC0:
  .string "%d"
main:
  pushq %rbp
  movq %rsp, %rbp
  subq $16, %rsp
  movl $5, -4(%rbp)
  movl $10, -8(%rbp)
  movl -8(%rbp), %eax
  addl %eax, -4(%rbp)
  movl -4(%rbp), %eax
  movl %eax, %esi
  movl $.LC0, %edi
  movl $0, %eax
  call printf
  movl $0, %eax
  leave
  ret


