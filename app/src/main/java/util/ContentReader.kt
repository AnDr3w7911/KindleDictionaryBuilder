package util

import dictionary.Word
import java.io.*

/**
 * Reader to read the Words with their inflections and definitions from a file
 */
class ContentReader(content: InputStream) : Closeable {
    private val reader: BufferedReader

    init {
        reader = content.bufferedReader()
    }

    @Throws(IOException::class)
    fun readContent(): List<Word> {
        val content: MutableList<Word> = ArrayList()
        while (reader.ready()) {
            val line = reader.readLine()
            if (line.isNotBlank()) {
                val tokens = line.split("::")
                if (tokens.size == 2) {
                    val words = tokens[0].split(", ")
                    val inflections = words.asSequence().drop(1).toList()
                    content.add(Word(words[0], tokens[1], inflections))
                } else {
                    throw IOException("Invalid input: $line")
                }
            }
        }
        return content
    }

    @Throws(IOException::class)
    override fun close() {
        reader.close()
    }
}