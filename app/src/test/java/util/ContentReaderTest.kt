package util

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.io.FileNotFoundException
import java.io.IOException

class ContentReaderTest {

    @Test
    @Throws(IOException::class)
    fun getContent(){
            val input = javaClass.classLoader.getResourceAsStream("definitions.txt")
            ContentReader(input).use { reader ->
                val content = reader.readContent()
                Assertions.assertEquals("Word1", content[0].word)
                Assertions.assertEquals("definition1", content[0].definition)
                Assertions.assertEquals(1, content[0].inflections.size)
                Assertions.assertEquals("word 1", content[0].inflections[0])
                Assertions.assertEquals("Word2", content[1].createHtmlWord())
                Assertions.assertEquals("definition2", content[1].definition)
            }
        }

    @Test
    @Throws(FileNotFoundException::class, IOException::class)
    fun invalidInputFileExtraTabs() {
        val input = javaClass.classLoader.getResourceAsStream("invalidDefs.txt")
        ContentReader(input).use { reader -> Assertions.assertThrows(IOException::class.java) { reader.readContent() } }
    }
}