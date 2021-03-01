package wacc.frontend.ast.expression

import wacc.backend.CodeGenerator
import wacc.backend.CodeGenerator.freeCalleeReg
import wacc.backend.CodeGenerator.seeLastUsedCalleeReg
import wacc.backend.instruction.Instruction
import wacc.backend.instruction.enums.Condition
import wacc.backend.instruction.enums.Register
import wacc.backend.instruction.instrs.*
import wacc.backend.instruction.utils.*
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
        val instr = mutableListOf<Instruction>()
        instr.addAll(expr1.translate())
        val reg1 = seeLastUsedCalleeReg()
        instr.addAll(expr2.translate())
        val reg2 = seeLastUsedCalleeReg()
        when (binOp) {
            BinOp.PLUS -> {
                instr.add(AddInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true, null))
                instr.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.MINUS -> {
                instr.add(SubInstr(Condition.AL, reg1, reg1, RegisterOperand(reg2), true, null))
                instr.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.MULT -> {
                instr.add(MultInstr(Condition.AL, reg1, reg2, reg1, reg2))
                instr.add(CompareInstr(reg2, RegShiftOffsetOperand(reg1, ShiftType.ASR, 31))) //TODO( MIGHT NEED TO ADD ASR)
                instr.add(BranchInstr(Condition.NE, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            BinOp.DIV -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                instr.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                instr.add(BranchInstr(Condition.AL, RuntimeError.divideZeroCheckLabel, true))
                CodeGenerator.runtimeErrors.addDivideByZeroCheck()
                instr.add(BranchInstr(Condition.AL, Label("__aeabi_idiv"), true))
                instr.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R0)))
            }
            BinOp.MOD -> {
                instr.add(MoveInstr(Condition.AL, Register.R0, RegisterOperand(reg1)))
                instr.add(MoveInstr(Condition.AL, Register.R1, RegisterOperand(reg2)))
                instr.add(BranchInstr(Condition.AL, RuntimeError.divideZeroCheckLabel, true))
                CodeGenerator.runtimeErrors.addDivideByZeroCheck()
                instr.add(BranchInstr(Condition.AL, Label("__aeabi_idivmod"), true))
                instr.add(MoveInstr(Condition.AL, reg1, RegisterOperand(Register.R1)))
            }
            BinOp.EQ -> {
                instr.add(CompareInstr(reg1, RegisterOperand(reg2)))
                instr.add(MoveInstr(Condition.EQ, reg1, ImmediateOperandBool(true)))
                instr.add(MoveInstr(Condition.NE, reg1, ImmediateOperandBool(false)))
            }

            BinOp.NEQ -> {
                instr.add(CompareInstr(reg1, RegisterOperand(reg2)))
                instr.add(MoveInstr(Condition.NE, reg1, ImmediateOperandBool(true)))
                instr.add(MoveInstr(Condition.EQ, reg1, ImmediateOperandBool(false)))
            }
            BinOp.LTE -> {
                instr.add(CompareInstr(reg1, RegisterOperand(reg2)))
                instr.add(MoveInstr(Condition.LE, reg1, ImmediateOperandBool(true)))
                instr.add(MoveInstr(Condition.GT, reg1, ImmediateOperandBool(false)))
            }
            BinOp.LT -> {
                instr.add(CompareInstr(reg1, RegisterOperand(reg2)))
                instr.add(MoveInstr(Condition.LT, reg1, ImmediateOperandBool(true)))
                instr.add(MoveInstr(Condition.GE, reg1, ImmediateOperandBool(false)))
            }
            BinOp.GTE -> {
                instr.add(CompareInstr(reg1, RegisterOperand(reg2)))
                instr.add(MoveInstr(Condition.GE, reg1, ImmediateOperandBool(true)))
                instr.add(MoveInstr(Condition.LT, reg1, ImmediateOperandBool(false)))
            }
            BinOp.GT -> {
                instr.add(CompareInstr(reg1, RegisterOperand(reg2)))
                instr.add(MoveInstr(Condition.GT, reg1, ImmediateOperandBool(true)))
                instr.add(MoveInstr(Condition.LE, reg1, ImmediateOperandBool(false)))
            }

            BinOp.AND -> {
                instr.add(AndInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
            }
            BinOp.OR -> {
                instr.add(OrInstrType(Condition.AL, reg1, reg1, RegisterOperand(reg2)))
            }
        }
        freeCalleeReg()
        return instr
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
        val instr = mutableListOf<Instruction>()
        instr.addAll(expr.translate())
        val reg1 = seeLastUsedCalleeReg()
        when (unOp) {
            UnOp.NOT -> {
                instr.add(XorInstrType(Condition.AL, reg1, reg1, ImmediateOperandInt(1)))
            }
            UnOp.MINUS -> {
                instr.add(ReverseSubInstr(Condition.AL, reg1, reg1, ImmediateOperandInt(0), true, null))
                instr.add(BranchInstr(Condition.VS, RuntimeError.throwOverflowErrorLabel, true))
                CodeGenerator.runtimeErrors.addOverflowError()
            }
            UnOp.LEN -> {
                instr.add(LoadInstr(Condition.AL, null, RegisterAddr(Register.SP), reg1))
                instr.add(LoadInstr(Condition.AL, null, RegisterAddr(reg1), reg1))
//                instr.add(LoadInstr(Condition.AL, null, ImmediateInt((expr as ArrayElemAST).indices.size), reg1))
//                  consider the case when expr is a variable
            }
            UnOp.ORD -> {
//                instr.add(MoveInstr(Condition.AL, reg1,  ImmediateOperandChar((expr as CharLiterAST).value)))
            }
            UnOp.CHR -> {
//                instr.add(LoadInstr(reg1, null, ImmediateInt((expr as IntLiterAST).value), Condition.AL))
            }
        }
        return instr
    }
}

enum class UnOp {
    NOT, MINUS, LEN, ORD, CHR
}
