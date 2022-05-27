package dictionary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Path

class ContentTest {
    @Test
    @Throws(IOException::class)
    fun createContent(@TempDir tempDir: Path) {
        val words = this::class.java.classLoader.getResource("definitions.txt")
        Assertions.assertDoesNotThrow { ContentPage(tempDir.toString() + File.separator, words!!.path).build() }
        val outputFiles = tempDir.toFile().listFiles()
        Assertions.assertNotNull(outputFiles)
        Assertions.assertEquals(1, outputFiles!!.size)
        Assertions.assertEquals(ContentPage.FILE_NAME, outputFiles[0].name)
    }

    @Test
    fun invalidContent(@TempDir tempDir: Path) {
        Assertions.assertThrows(FileNotFoundException::class.java) { ContentPage(tempDir.toString(), "%^&*?//") }
    }
}