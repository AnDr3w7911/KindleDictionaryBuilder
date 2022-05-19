package util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dictionary.Word;

public class ContentReader implements Closeable {
    private final BufferedReader reader;

    public ContentReader(InputStream content) {
        reader = new BufferedReader(new InputStreamReader(content, StandardCharsets.UTF_8));
    }

    public List<Word> readContent() throws IOException {
        List<Word> content = new ArrayList<>();
        while (reader.ready()) {
            String line = reader.readLine();
            if (!line.isBlank()) {
                String[] tokens = line.split("::");
                if (tokens.length == 2) {
                    String[] words = tokens[0].split(", ");
                    List<String> inflections = Stream.of(words).skip(1).collect(Collectors.toList());
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
