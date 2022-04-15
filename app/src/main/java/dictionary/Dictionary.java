package dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    public static class DictionaryBuilder {
        private final String outputPath;
        private final List<DictionaryPage> pages;

        public DictionaryBuilder(String outputPath) {
            this.outputPath = outputPath;
            pages = new ArrayList<>();
        }

        public Dictionary build() throws IOException {
            return new Dictionary(this);
        }

        public DictionaryBuilder cover(String title, String createdBy) {
            pages.add(new CoverPage(title, createdBy, outputPath));
            return this;
        }

        public DictionaryBuilder content(String contentPath) throws FileNotFoundException {
            pages.add(new ContentPage(outputPath, contentPath));
            return this;
        }

        public DictionaryBuilder copyright() {
            pages.add(new CopyrightPage(outputPath));
            return this;
        }
    }

    private final List<DictionaryPage> pages;

    private Dictionary(DictionaryBuilder builder) throws IOException {
        System.out.println("Creating Custom Kindle Dictionary...");
        pages = builder.pages;
        if (!pages.isEmpty()) {
            for (var page : pages) {
                System.out.println("Creating: " + page.getOutFile().getName());
                page.build();
            }
            System.out.println(
                    "Dictionary files created: " + pages.get(0).getOutFile().getParentFile().getAbsolutePath());
        } else {
            System.out.println("No Files created.");
        }
    }

}
