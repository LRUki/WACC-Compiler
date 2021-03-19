package extension

import frontend.actionOnFiles
import org.junit.Test
import wacc.WaccFile
import wacc.frontend.ast.expression.*
import wacc.frontend.ast.program.ProgramAST
import wacc.frontend.ast.statement.block.IfStatAST
import wacc.frontend.ast.statement.block.WhileStatAST
import wacc.frontend.ast.statement.nonblock.ActionStatAST
import wacc.frontend.ast.statement.nonblock.AssignStatAST
import wacc.frontend.ast.statement.nonblock.DeclareStatAST
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ConstantPropagationTest {
    val path = "extension_wacc/valid/optimisation/const_prop"


    @Test
    fun propogationForOptimisableFiles() {
        val folder = File(path)
        actionOnFiles(folder) { file ->
            val waccFile = WaccFile(file)
            waccFile.frontend()
            val oldAST = waccFile.ast
            waccFile.constEvaluation()
            waccFile.constPropagation()
            val newAST = waccFile.ast
            assertFalse { oldAST.equals(newAST) }
        }
    }

    @Test
    fun constantPropOnSimpleBoolFile() {
        val waccFile = WaccFile(File("$path/boolCalc.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is BinOpExprAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is BoolLiterAST)
    }

    @Test
    fun constantPropOnFileWithNoPropDoesNothing() {
        val waccFile = WaccFile(File("$path/noProp.wacc"))
        waccFile.frontend()
        val oldAst = waccFile.ast

        waccFile.constPropagation()
        val newAst = waccFile.ast

        assertFalse { oldAst.equals(newAst) }
    }

    @Test
    fun constantPropOnComplexFile(){
        val waccFile = WaccFile(File("$path/complexProp.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[1] as DeclareStatAST).rhs is BinOpExprAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is BinOpExprAST)
        assertTrue((((waccFile.ast as ProgramAST).stats[5] as IfStatAST).cond is IdentAST))
        assertTrue((((waccFile.ast as ProgramAST).stats[5] as IfStatAST).thenBody[1] as DeclareStatAST).rhs is BinOpExprAST)
        assertTrue((((waccFile.ast as ProgramAST).stats[7] as WhileStatAST).cond is BinOpExprAST))
        assertTrue((((waccFile.ast as ProgramAST).stats[7] as WhileStatAST).body[0] as DeclareStatAST).rhs is UnOpExprAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[1] as DeclareStatAST).rhs is IntLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is IntLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[5] as IfStatAST).cond is BoolLiterAST)
        assertTrue((((waccFile.ast as ProgramAST).stats[5] as IfStatAST).thenBody[1] as DeclareStatAST).rhs is IntLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[7] as WhileStatAST).cond is BoolLiterAST)
        assertTrue((((waccFile.ast as ProgramAST).stats[7] as WhileStatAST).body[0] as DeclareStatAST).rhs is BoolLiterAST)
    }

    @Test
    fun constantPropOnStructAccess() {
        val waccFile = WaccFile(File("$path/structAssignOptim.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is StructAccessAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as DeclareStatAST).rhs is StructAccessAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is IntLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as DeclareStatAST).rhs is CharLiterAST)
    }

    @Test
    fun constantPropOnActionStat() {
        val waccFile = WaccFile(File("$path/longExpr.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[1] as DeclareStatAST).rhs is BinOpExprAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is BinOpExprAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as ActionStatAST).expr is IdentAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[1] as DeclareStatAST).rhs is IntLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is IntLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as ActionStatAST).expr is IntLiterAST)
    }

    @Test
    fun constantPropOnAssignStat() {
        val waccFile = WaccFile(File("$path/simpleAssignment.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as AssignStatAST).rhs is BinOpExprAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as AssignStatAST).rhs is IntLiterAST)
    }

    @Test
    fun constantPropOnBinOpExpr() {
        val waccFile = WaccFile(File("$path/simpleBinOp.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is BinOpExprAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[2] as DeclareStatAST).rhs is IntLiterAST)
    }

    @Test
    fun constantPropUnOpExpr() {
        val waccFile = WaccFile(File("$path/simpleUnOp.wacc"))
        waccFile.frontend()
        assertTrue(((waccFile.ast as ProgramAST).stats[1] as DeclareStatAST).rhs is UnOpExprAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as DeclareStatAST).rhs is UnOpExprAST)
        waccFile.constPropagation()
        assertTrue(((waccFile.ast as ProgramAST).stats[1] as DeclareStatAST).rhs is BoolLiterAST)
        assertTrue(((waccFile.ast as ProgramAST).stats[3] as DeclareStatAST).rhs is IntLiterAST)
    }

    @Test
    fun constantPropDoesNothingInPointerContext() {
        val waccFile = WaccFile(File("$path/pointerContext.wacc"))
        waccFile.frontend()
        val oldAst = waccFile.ast
        waccFile.constPropagation()
        val newAst = waccFile.ast
        assertFalse { oldAst.equals(newAst) }
    }

}
