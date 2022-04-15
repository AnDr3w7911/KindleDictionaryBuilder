package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CopyrightPageTest {

    @Test
    public void createPage(@TempDir Path tempDir){
        assertDoesNotThrow(() -> new CopyrightPage(tempDir.toString() + File.separator).build());
        File[] outputFiles = tempDir.toFile().listFiles();
        assertEquals(1, outputFiles.length);
        assertEquals(CopyrightPage.FILE_NAME, outputFiles[0].getName());
    }
    
}
