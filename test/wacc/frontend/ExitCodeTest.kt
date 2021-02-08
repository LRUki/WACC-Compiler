package wacc.frontend

import CompilerEmulator
import org.junit.Assert.*
import org.junit.Test
import java.io.File


class ExitCodeTest {


    @Test
    fun validFilesReturnExitCode0(){
    File("wacc_examples/valid").walkTopDown().forEach {
        if (it.path.endsWith("wacc")) {
            val result = CompilerEmulator(it.path).emulate()
            val exitCode = result?.exitCode
            assertTrue(exitCode == 0)
            }
        }
    }

    @Test
    fun filesWithSyntaxErrorReturnExitCode100() {
    File("wacc_examples/invalid/syntaxErr").walkTopDown().forEach {
            if (it.path.endsWith("wacc")) {
                val result = CompilerEmulator(it.path).emulate()
                val exitCode = result?.exitCode
                println(exitCode)
                assertTrue(exitCode == 100)
            }
        }
    }

//    @Test
//    fun filesWithSemanticErrorReturnExitCode200() {
//    File("wacc_examples/invalid/syntaxErr").walkTopDown().forEach {
//            if (it.path.endsWith("wacc")) {
//                val result = TestCompiler(it.path).testCompile()
//                val exitCode = result?.exitCode
//                assertTrue(exitCode == 200)
//            }
//        }
//    }


}