package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class CoverPageTest {


    @Test
    public void createCoverPage(@TempDir Path tempDir){
        assertDoesNotThrow(()-> new CoverPage("title", "Me", tempDir.toString()).build());
    }
}
