package DictionaryBuilder;

import java.util.List;

public class Word {
    private final String word;
    private final String definition;
    private List<String> inflections;

    public Word(String word, String definition){
        this.word = word;
        this.definition = definition;
    }

    public Word(String word, String definition, List<String> inflections){
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

}
