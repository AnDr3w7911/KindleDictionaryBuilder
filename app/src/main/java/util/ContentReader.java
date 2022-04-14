package util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dictionary.Word;

public class ContentReader implements Closeable {
    private final BufferedReader reader;

    public ContentReader(InputStream content) throws FileNotFoundException {
        reader = new BufferedReader(new InputStreamReader(content));
    }

    public List<Word> readContent() throws IOException {
        List<Word> content = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            if (!line.isBlank()) {
                String[] tokens = line.split("::");
                if (tokens.length == 2) {
                    String[] words = tokens[0].split(", ");
                    List<String> inflections = null;
                    if (words.length > 1) {
                        inflections = Stream.of(words).skip(1).collect(Collectors.toList());
                    }
                    content.add(new Word(words[0], tokens[1], inflections));
                } else {
                    throw new IOException("Invalid input: " + line);
                }
            }
        }

        return content;
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
