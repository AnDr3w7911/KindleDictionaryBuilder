package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

import util.ContentReader;

public class ContentPage extends DictionaryPage {
    public static final String FILE_NAME = "content.html";
    private static final String WORD_TAG = "[PUT THE WORDS HERE]";

    private File contentFile;

    public ContentPage(String outputPath, String contentPath) throws FileNotFoundException{
        super(outputPath + FILE_NAME);
        this.contentFile = new File(contentPath);
        if(!contentFile.exists()){
            throw new FileNotFoundException(contentPath);
        }
    }

    private List<Word> readWords() throws IOException{
        try(ContentReader reader = new ContentReader(new FileInputStream(contentFile))){
            return reader.readContent();
        }
    }

    @Override
    public void build() throws IOException {
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(ContentPage.class.getClassLoader().getResourceAsStream("contentTemplate.html")));
                PrintWriter writer = new PrintWriter(getOutFile(), StandardCharsets.UTF_8)) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (WORD_TAG.equals(line.trim())) {
                    writer.println();
                    for (Word word : readWords()) {
                        writer.println(word.getBlock());
                        writer.println();
                    }
                } else {
                    writer.println(line);
                }
                writer.flush();
            }
        }
        
    }

}
