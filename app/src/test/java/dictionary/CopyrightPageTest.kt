package dictionary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class CopyrightPageTest {
    @Test
    fun createPage(@TempDir tempDir: Path) {
        Assertions.assertDoesNotThrow { CopyrightPage(tempDir.toString() + File.separator).build() }
        val outputFiles = tempDir.toFile().listFiles()
        Assertions.assertNotNull(outputFiles)
        Assertions.assertEquals(1, outputFiles!!.size)
        Assertions.assertEquals(CopyrightPage.FILE_NAME, outputFiles[0].name)
    }
}