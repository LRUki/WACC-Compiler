package frontend

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import wacc.frontend
import frontend.Utils.exitCode
import wacc.frontend.exception.SemanticException
import wacc.frontend.exception.SyntaxException
import java.io.File

object Utils {
    val PATH_TO_EXAMPLES = "wacc_examples/"
    var exitCode: Int = 0
}


class CompilerResult(val exitCode: Int, val exception: Exception?)

suspend fun emulate(fileName: String): CompilerResult? {
    var exitCode = 0
    var exception: Exception? = null
    val file = File(fileName)
    try {
        val ast = frontend(file)
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

fun <T> startErrorListenerWithoutExit(errorChannel: Channel<T>): Job {
    return GlobalScope.launch {
        val allErrors = mutableListOf<T>()
        for (error in errorChannel) {
            allErrors.add(error)
        }
        var count = 0
        allErrors.forEach { System.err.println("${count++} $it") }
        if (allErrors.size > 0) {
            when (val err = allErrors[0]) {
                is SemanticException -> exitCode = 200;
                is SyntaxException -> exitCode = 100
            }
        } else {
            exitCode = 0
        }
    }
}