package DictionaryBuilder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class CoverPageTest {
    @Test
    public void createCoverPage(){
        assertDoesNotThrow(()-> CoverPage.createCoverPage("title", "Me", "output/"));
    }
}
