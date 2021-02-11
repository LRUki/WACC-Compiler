package wacc.frontend

import wacc.frontend
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxException
import java.io.File

object Utils {
    val PATH_TO_EXAMPLES = "wacc_examples/"
}


class CompilerResult(val exitCode: Int, val exception: Exception?)

fun emulate(fileName: String): CompilerResult? {
    var exitCode = 0
    var exception: Exception? = null
    val file = File(fileName)
    try {
        val ast = frontend(file.inputStream())
    } catch (e: SyntaxException) {
        System.err.println("Syntax Error in file: $fileName")
        exception = e
        exitCode = 100
    } catch (e: SemanticException) {
        System.err.println("Semantic Error in file: $fileName")
        exception = e
        exitCode = 200
    } catch (e: Exception) {
        System.err.println("Error in file: $fileName" + e.message)
        System.err.println("An exception was thrown that was not syntax or semantic")
        exitCode = 1
    }
    return CompilerResult(exitCode, exception)
}

fun <T> actionOnFiles(file: File, action: (File) -> T): List<T> {
    var list = emptyList<T>()
    if (file.isDirectory) {
        for (subFile in file.listFiles()!!) {
            list += actionOnFiles(subFile, action)
        }
    } else {
        list += action(file)
    }
    return list
}
