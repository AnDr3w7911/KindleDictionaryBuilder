package util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

public class ContentReaderTest {

    @Test
    public void getContent() throws FileNotFoundException, IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("definitions.txt");
        try (ContentReader reader = new ContentReader(input)) {
            var content = reader.readContent();

            assertEquals("Word1", content.get(0).getWord());
            assertEquals("definition1", content.get(0).getDefinition());
            assertEquals(1, content.get(0).getInflections().size());
            assertEquals("word 1", content.get(0).getInflections().get(0));
            assertEquals("Word2", content.get(1).getWord());
            assertEquals("definition2", content.get(1).getDefinition());
        }
    }

    @Test
    public void invalidInputFileExtraTabs() throws FileNotFoundException, IOException {
        InputStream input = getClass().getClassLoader().getResourceAsStream("invalidDefs.txt");
        try (ContentReader reader = new ContentReader(input)) {
            assertThrows(IOException.class, () -> reader.readContent());
        }
    }
}
