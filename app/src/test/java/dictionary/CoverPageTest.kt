package dictionary

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import java.io.File
import java.nio.file.Path

class CoverPageTest {
    @Test
    fun createCoverPage(@TempDir tempDir: Path) {
        Assertions.assertDoesNotThrow { CoverPage("title", "Me", tempDir.toString() + File.separator).build() }
        val outputFiles = tempDir.toFile().listFiles()
        Assertions.assertNotNull(outputFiles)
        Assertions.assertEquals(1, outputFiles!!.size)
        Assertions.assertEquals(CoverPage.FILE_NAME, outputFiles[0].name)
    }
}