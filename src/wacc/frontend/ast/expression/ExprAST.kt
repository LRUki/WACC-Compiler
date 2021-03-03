package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.translate.RuntimeError
import wacc.backend.translate.instruction.Instruction
import wacc.backend.translate.instruction.instructionpart.Condition
import wacc.backend.translate.instruction.instructionpart.Register
import wacc.backend.translate.instruction.instructionpart.ShiftType
import wacc.backend.translate.instruction.*
import wacc.backend.translate.instruction.instructionpart.*
import wacc.frontend.SymbolTable
import wacc.frontend.ast.AbstractAST
import wacc.frontend.ast.assign.RhsAST
import wacc.frontend.ast.type.ArrayTypeAST
import wacc.frontend.ast.type.BaseType
import wacc.frontend.ast.type.BaseTypeAST
import wacc.frontend.ast.type.TypeAST
import wacc.frontend.ast.type.TypeInstance.boolTypeInstance
import wacc.frontend.ast.type.TypeInstance.charTypeInstance
import wacc.frontend.ast.type.TypeInstance.intTypeInstance
import wacc.frontend.ast.type.TypeInstance.stringTypeInstance
import wacc.frontend.exception.semanticError

interface ExprAST : RhsAST

/**
 * AST node to represent an expression with a Binary Operation
 *
 * @property binOp Operator to perform to expressions, will be one defined in the BinOp Enum
 * @property expr1 First Expression
 * @property expr2 Second Expression
 */
class BinOpExprAST(val binOp: BinOp, val expr1: ExprAST, val expr2: ExprAST) : ExprAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        symTable = table
        if (!expr1.check(table) || !expr2.check(table)) {
            return false
        }

        val type1 = expr1.getRealType(table)
        val type2 = expr2.getRealType(table)

        if (type1 != type2) {
            semanticError("Expected type $type1, Actual type $type2", ctx)
            return false
        }
        when (binOp) {
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                if (type1 == intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $type1", ctx)
            }
            BinOp.LTE, BinOp.LT, BinOp.GTE, BinOp.GT -> {
                if (type1 == intTypeInstance ||
                        type1 == charTypeInstance ||
                        type1 == stringTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, CHAR or STRING, Actual type $type1", ctx)
            }
            BinOp.AND, BinOp.OR -> {
                if (type1 == boolTypeInstance) {
                    return true
                }
                semanticError("Expected type BOOL, Actual type $type1", ctx)
            }
            else -> return true
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return when (binOp) {
            BinOp.MULT, BinOp.DIV, BinOp.MOD,
            BinOp.PLUS, BinOp.MINUS -> {
                BaseTypeAST(BaseType.INT)
            }

            BinOp.EQ, BinOp.NEQ, BinOp.LTE, BinOp.LT,
            BinOp.GTE, BinOp.GT, BinOp.AND, BinOp.OR -> {
                BaseTypeAST(BaseType.BOOL)
            }
        }
    }

    override fun translate(): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(expr1.translate())
        var reg1 = seeLastUsedCalleeReg()
        instrs.addAll(expr2.translate())
        var reg2 = seeLastUsedCalleeReg()

        var useAccumulator = false
        if (reg1 == Register.NONE || reg1 == Register.R10) {
            useAccumulator = true
            reg1 = Register.R10
            reg2 = Register.R11
        }

        when (binOp) {
            BinOp.PLUS -> {
                if (!useAccumulator) {
                    instrs.add(AddInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(AddInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                }
                instrs.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.MINUS -> {
                if (!useAccumulator) {
                    instrs.add(SubInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(SubInstr(Condition.AL, reg1, reg2, RegisterOperand(reg1), true))
                }
                instrs.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.MULT -> {
                if (!useAccumulator) {
                    instrs.add(MultInstr(Condition.AL, reg1, reg2, reg1, reg2))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MultInstr(Condition.AL, reg1, reg2, reg2, reg1))
                }
                instrs.add(CompareInstr(reg2, RegShiftOffsetOperand(reg1, ShiftType.ASR, 31)))
                instrs.add(BranchInstr(Condition.NE, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.DIV -> {
                if (!useAccumulator) {
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg2)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg1)))
                }
                instrs.add(BranchInstr(Condition.AL, RuntimeError.divideZeroCheckLabel, true))
                CodeGenerator.runtimeErrors.addDivideByZeroCheck()
                instrs.add(BranchInstr(Condition.AL, Label("__aeabi_idiv"), true))
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R0)))
            }
            BinOp.MOD -> {
                if (!useAccumulator) {
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg2)))
                    instrs.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg1)))
                }
                instrs.add(BranchInstr(Condition.AL, RuntimeError.divideZeroCheckLabel, true))
                CodeGenerator.runtimeErrors.addDivideByZeroCheck()
                instrs.add(BranchInstr(Condition.AL, Label("__aeabi_idivmod"), true))
                instrs.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R1)))
            }
            BinOp.EQ -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.EQ, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.NE, reg1, ImmediateBoolOperand(false)))
            }

            BinOp.NEQ -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.NE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.EQ, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.LTE -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.LT -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.GTE -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.GE, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.LT, reg1, ImmediateBoolOperand(false)))
            }
            BinOp.GT -> {
                if (!useAccumulator) {
                    instrs.add(CompareInstr(reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(CompareInstr(reg2, RegisterOperand(reg1)))
                }
                instrs.add(MoveInstr(Condition.GT, reg1, ImmediateBoolOperand(true)))
                instrs.add(MoveInstr(Condition.LE, reg1, ImmediateBoolOperand(false)))
            }

            BinOp.AND -> {
                if (!useAccumulator) {
                    instrs.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))

                }
            }
            BinOp.OR -> {
                if (!useAccumulator) {
                    instrs.add(OrInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
                } else {
                    instrs.add(PopInstr(Register.R11))
                    instrs.add(OrInstrType(Condition.AL, reg1, reg2, RegisterOperand(reg1)))
                }
            }
        }
        if (!useAccumulator) {
            freeCalleeReg()
        }
        return instrs
    }

}

enum class BinOp {
    MULT, DIV, MOD,
    PLUS, MINUS,
    LTE, LT, GTE, GT,
    EQ, NEQ,
    AND,
    OR
}

/**
 * AST node to represent an expression with a Unary Operation
 *
 * @property unOp Operation to perform on the expression, chosen from the UnOp Enum
 * @property expr Expression to operate on
 */
class UnOpExprAST(val unOp: UnOp, val expr: ExprAST) : ExprAST, AbstractAST() {

    override fun check(table: SymbolTable): Boolean {
        if (!expr.check(table)) {
            return false
        }
        val exprType = expr.getRealType(table)

        when (unOp) {
            UnOp.NOT -> {
                if (exprType == boolTypeInstance) {
                    return true
                }
                semanticError("Expected type BOOL, Actual type $exprType", ctx)
            }
            UnOp.MINUS, UnOp.CHR -> {
                if (exprType == intTypeInstance) {
                    return true
                }
                semanticError("Expected type INT, Actual type $exprType", ctx)
            }
            UnOp.LEN -> {
                if (exprType is ArrayTypeAST) {
                    return true
                }
                semanticError("Expected type ARRAY, Actual type $exprType", ctx)
            }
            UnOp.ORD -> {
                if (exprType == charTypeInstance) {
                    return true
                }
                semanticError("Expected type CHAR, Actual type $exprType", ctx)
            }
        }
        return false
    }

    override fun getRealType(table: SymbolTable): TypeAST {
        return when (unOp) {
            UnOp.NOT -> BaseTypeAST(BaseType.BOOL)
            UnOp.CHR -> BaseTypeAST(BaseType.CHAR)
            UnOp.MINUS, UnOp.LEN, UnOp.ORD -> BaseTypeAST(BaseType.INT)
        }
    }

    override fun translate(): List<Instruction> {
        val instrs = mutableListOf<Instruction>()
        instrs.addAll(expr.translate())
        val reg1 = seeLastUsedCalleeReg()
        when (unOp) {
            UnOp.NOT -> {
                instrs.add(XorInstrType(Condition.AL, reg1, reg1, ImmediateIntOperand(1)))
            }
            UnOp.MINUS -> {
                instrs.add(ReverseSubInstr(Condition.AL, reg1, reg1, ImmediateIntOperand(0), true))
                instrs.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            UnOp.LEN -> {
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(Register.SP), reg1))
                instrs.add(LoadInstr(Condition.AL, null, RegisterMode(reg1), reg1))
//                instrs.add(LoadInstr(Condition.AL, null, ImmediateInt((expr as ArrayElemAST).indices.size), reg1))
//                  consider the case when expr is a variable
            }
            UnOp.ORD -> {
//                instrs.add(MoveInstr(Condition.AL, reg1,  ImmediateOperandChar((expr as CharLiterAST).value)))
            }
            UnOp.CHR -> {
//                instrs.add(LoadInstr(reg1, null, ImmediateInt((expr as IntLiterAST).value), Condition.AL))
            }
        }
        return instrs
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
