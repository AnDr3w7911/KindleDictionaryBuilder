package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CoverPageTest {

    @Test
    public void createCoverPage(@TempDir Path tempDir) {
        assertDoesNotThrow(() -> new CoverPage("title", "Me", tempDir.toString() + File.separator).build());
        File[] outputFiles = tempDir.toFile().listFiles();
        assertEquals(1, outputFiles.length);
        assertEquals(CoverPage.FILE_NAME, outputFiles[0].getName());
    }
}
