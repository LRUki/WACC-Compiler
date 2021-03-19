.data
.LC0:
  .string "%d\n"

.text
.global main
main:
  pushq %rbp
  movq %rsp, %rbp
  subq $16, %rsp
  movl $15, -4(%rbp)
  movl $10, -8(%rbp)
  movl -8(%rbp), %eax
  addl %eax, -4(%rbp)
  movl -4(%rbp), %eax
  movl %eax, %esi
  movl $.LC0, %edi
  movl $0, %eax
  call printf
  movq $0, %rax
  leave
  ret


