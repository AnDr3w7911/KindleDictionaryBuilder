package dictionary

import util.ContentReader
import java.io.*

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
        ContentReader(contentFile.inputStream()).use { reader -> return reader.readContent().asSequence().sorted().toList() }
    }

    @Throws(IOException::class)
    override fun build() {
        val stream = this::class.java.classLoader.getResourceAsStream("contentTemplate.html")
        stream?.bufferedReader()?.use { reader ->
            {
                outFile.printWriter().use { writer ->
                    {
                        while (reader.ready()) {
                            val line = reader.readLine()
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
        }
    }

    companion object {
        const val FILE_NAME = "content.html"
        private const val WORD_TAG = "[PUT THE WORDS HERE]"
    }
}