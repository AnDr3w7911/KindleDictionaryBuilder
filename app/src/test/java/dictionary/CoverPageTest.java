package dictionary;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class CoverPageTest {
    @Test
    public void createCoverPage(){
        assertDoesNotThrow(()-> new CoverPage("title", "Me", "output/").build());
    }
}
