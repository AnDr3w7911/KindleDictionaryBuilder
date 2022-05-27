package dictionary

import dictionary.Dictionary.DictionaryBuilder
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.io.FileNotFoundException
import java.nio.file.Path
import java.util.stream.Collectors
import java.util.stream.Stream

class DictionaryTest {
    @Test
    @Throws(FileNotFoundException::class)
    fun createCompleteDictionary(@TempDir tempDir: Path) {
        val words = this::class.java.classLoader.getResource("definitions.txt")
        val builder = DictionaryBuilder(tempDir.toString() + File.separator)
        Assertions.assertDoesNotThrow {
            builder.cover("title", "createdBy")
                .copyright()
                .content(words!!.path)
                .build()
        }
        val outputFiles = tempDir.toFile().listFiles()
        Assertions.assertEquals(3, outputFiles?.size)
        val fileNames = Stream.of(*outputFiles).map { obj: File -> obj.name }
            .collect(Collectors.toList())
        Assertions.assertTrue(fileNames.contains(CoverPage.FILE_NAME))
        Assertions.assertTrue(fileNames.contains(CopyrightPage.FILE_NAME))
        Assertions.assertTrue(fileNames.contains(ContentPage.FILE_NAME))
    }

    @Test
    fun noFiles(@TempDir tempDir: Path) {
        Assertions.assertDoesNotThrow<Dictionary> { DictionaryBuilder(tempDir.toString() + File.separator).build() }
        val outputFiles = tempDir.toFile().listFiles()
        Assertions.assertEquals(0, outputFiles?.size)
    }
}