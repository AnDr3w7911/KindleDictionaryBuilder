package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ContentTest {

    @Test
    public void createContent(@TempDir Path tempDir) throws IOException {
        URL words = getClass().getClassLoader().getResource("definitions.txt");
        assertDoesNotThrow(() -> new ContentPage(tempDir.toString() + File.separator, words.getPath()).build());
        File[] outputFiles = tempDir.toFile().listFiles();
        assertEquals(1, outputFiles.length);
        assertEquals(ContentPage.FILE_NAME, outputFiles[0].getName());
    }

    @Test
    public void invalidContent(@TempDir Path tempDir){
        assertThrows(FileNotFoundException.class, () -> new ContentPage(tempDir.toString(), "%^&*?//"));
    }

}
