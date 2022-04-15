package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import dictionary.Dictionary.DictionaryBuilder;

public class DictionaryTest {

    @Test
    public void createCompleteDictionary(@TempDir Path tempDir) throws FileNotFoundException {
        URL words = getClass().getClassLoader().getResource("definitions.txt");
        DictionaryBuilder builder = new DictionaryBuilder(tempDir.toString() + File.separator);
        assertDoesNotThrow(() -> {
            builder.cover("title", "createdBy")
                    .copyright()
                    .content(words.getPath())
                    .build();
        });

        File[] outputFiles = tempDir.toFile().listFiles();
        assertEquals(3, outputFiles.length);
        List<String> fileNames = Stream.of(outputFiles).map(File::getName).collect(Collectors.toList());
        assertTrue(fileNames.contains(CoverPage.FILE_NAME));
        assertTrue(fileNames.contains(CopyrightPage.FILE_NAME));
        assertTrue(fileNames.contains(ContentPage.FILE_NAME));
    }

    @Test
    public void noFiles(@TempDir Path tempDir){
        assertDoesNotThrow(() -> new DictionaryBuilder(tempDir.toString() + File.separator).build());
        File[] outputFiles = tempDir.toFile().listFiles();
        assertEquals(0, outputFiles.length);
    }

}
