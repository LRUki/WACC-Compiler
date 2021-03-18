package extension

import frontend.actionOnFiles
import org.junit.Test
import wacc.WaccFile
import wacc.backend.generateCode
import wacc.extension.optimization.ConstantEvaluationVisitor
import wacc.frontend.ast.program.ProgramAST
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ConstantPropagationTest {
    val path = "extension_wacc/valid/optimization/const_prop"


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
    fun banana2() {
    }

    @Test
    fun helpme() {
    }

    @Test
    fun banana() {
    }

    @Test
    fun pointersCannotBePropogated() {
    }

    @Test
    fun structTest() {

    }

}
