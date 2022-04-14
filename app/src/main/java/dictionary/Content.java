package dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;

import util.ContentReader;

public class Content extends DictionaryPage {
    private static final String FILE_NAME = "content.html";
    private static final String WORD_TAG = "[PUT THE WORDS HERE]";
    public static final String CONTENT_BLOCK = "\t\t<idx:entry name=\"default\" scriptable=\"yes\" spell=\"yes\">\n\t\t\t"
            +
            "<h5><dt><idx:orth>%s</idx:orth></dt></h5>\n\t\t\t<dd>%s</dd>\n\t\t</idx:entry>\n\t\t<hr/>";

    private File contentFile;

    public Content(String outputPath, String contentPath) throws FileNotFoundException{
        super(outputPath + FILE_NAME);
        this.contentFile = new File(contentPath);
        if(!contentFile.exists()){
            throw new FileNotFoundException(contentPath);
        }
    }

    public static String[] createBlocks(List<Word> content) {
        return content.stream()
                .map((entry) -> String.format(CONTENT_BLOCK, entry.getWord(), entry.getDefinition()))
                .toArray(String[]::new);
    }

    public static void createContentFile(String outputPath, List<Word> content) throws IOException {

        File file = new File(outputPath + FILE_NAME);
        System.out.println("Writing file: " + file.getAbsolutePath());
        file.getParentFile().mkdirs();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Content.class.getClassLoader().getResourceAsStream("contentTemplate.html")));
                PrintWriter writer = new PrintWriter(file)) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (WORD_TAG.equals(line.trim())) {
                    writer.println();
                    for (String word : createBlocks(content)) {
                        writer.println(word);
                        writer.println();
                    }
                } else {
                    writer.println(line);
                }
                writer.flush();
            }
        }

    }

    private String[] createBlocks() throws IOException{
        try(ContentReader reader = new ContentReader(new FileInputStream(contentFile))){
            return createBlocks(reader.readContent());
        }
    }

    @Override
    public void build() throws IOException {
        
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Content.class.getClassLoader().getResourceAsStream("contentTemplate.html")));
                PrintWriter writer = new PrintWriter(getOutFile())) {
            while (reader.ready()) {
                String line = reader.readLine();
                if (WORD_TAG.equals(line.trim())) {
                    writer.println();
                    for (String word : createBlocks()) {
                        writer.println(word);
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