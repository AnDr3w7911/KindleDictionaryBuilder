package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ContentTest {

    @Test
    public void test() throws IOException {
        List<Word> words = Arrays.asList(new Word("Word1", "Def1"),new Word( "Word2", "Def2"));
        assertDoesNotThrow(() -> Content.createContentFile("output/", words));
    }

}
