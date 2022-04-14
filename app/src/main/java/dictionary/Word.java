package dictionary;

import java.util.List;

public class Word {
    public static final String CONTENT_BLOCK = "\t\t<idx:entry name=\"default\" scriptable=\"yes\" spell=\"yes\">\n\t\t\t"
            + "<h5><dt><idx:orth>%s</idx:orth></dt></h5>\n\t\t\t<dd>%s</dd>\n\t\t</idx:entry>\n\t\t<hr/>";
    private final String word;
    private final String definition;
    private List<String> inflections;

    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public Word(String word, String definition, List<String> inflections) {
        this(word, definition);
        this.inflections = inflections;
    }

    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public List<String> getInflections() {
        return inflections;
    }

    public String getBlock(){
        return String.format(CONTENT_BLOCK, getWord(), getDefinition());
    }

}
