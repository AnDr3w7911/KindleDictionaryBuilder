package dictionary

import java.io.File
import java.io.IOException

abstract class DictionaryPage(outputPath: String) {
    val outFile: File

    init {
        outFile = File(outputPath)
        outFile.parentFile.mkdirs()
    }

    @Throws(IOException::class)
    abstract fun build()
}