package wacc.frontend

import java.io.File

//fun <T> actionOnFiles(file: File, action: (File) -> T): List<T> {
//    var list = emptyList<T>()
//    if (file.isDirectory) {
//        for (subFile in file.listFiles()) {
//            list += actionOnFiles(subFile, action)
//        }
//    } else {
//        list += action(file)
//    }
//    return list
//}