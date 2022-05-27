package dictionary

import util.ContentReader
import java.io.*

/**
 * The page that contains all the words and definitions
 */
class ContentPage @Throws(FileNotFoundException::class) constructor(outputPath: String, contentPath: String) :
    DictionaryPage(outputPath + FILE_NAME) {
    private val contentFile: File

    init {
        contentFile = File(contentPath)
        if (!contentFile.exists()) {
            throw FileNotFoundException(contentPath)
        }
    }

    @Throws(IOException::class)
    private fun readWords(): List<Word> {
        ContentReader(contentFile.inputStream()).use { reader -> return reader.readContent().sorted() }
    }

    @Throws(IOException::class)
    override fun build() {
        val stream = ContentPage::class.java.classLoader.getResourceAsStream("contentTemplate.html")
        outFile.printWriter().use { writer ->
            stream!!.bufferedReader(Charsets.UTF_8).useLines {
                it.forEach { line ->
                    if (WORD_TAG == line.trim()) {
                        writer.println()
                        for (word in readWords()) {
                            writer.println(word.block)
                            writer.println()
                        }
                    } else {
                        writer.println(line)
                    }
                    writer.flush()
                }
            }
        }
    }

    companion object {
        const val FILE_NAME = "content.html"
        private const val WORD_TAG = "[PUT THE WORDS HERE]"
    }
}