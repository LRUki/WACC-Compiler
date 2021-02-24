package frontend

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import java.io.File


class ExitCodeTest {


    @Test
    fun validFilesReturnExitCode0() {
        GlobalScope.launch {
            File("wacc_examples/valid").walkTopDown().forEach {
                if (it.path.endsWith("wacc")) {
                    val result = emulate(it.path)
                    val exitCode = result?.exitCode
                    assertThat(exitCode, `is`(0))
                }
            }
        }
    }

    @Test
    fun filesWithSyntaxErrorReturnExitCode100() {
        GlobalScope.launch {
            File("wacc_examples/invalid/syntaxErr").walkTopDown().forEach {
                if (it.path.endsWith("wacc")) {
                    val result = emulate(it.path)
                    val exitCode = result?.exitCode
                    assertThat(exitCode, `is`(100))
                }
            }
        }
    }

    @Test
    fun filesWithSemanticErrorReturnExitCode200() {
        GlobalScope.launch {
            File("wacc_examples/invalid/semanticErr").walkTopDown().forEach {
                if (it.path.endsWith("wacc")) {
                    val result = emulate(it.path)
                    val exitCode = result?.exitCode
                    assertThat(exitCode, `is`(200))
                }
            }
        }
    }

}