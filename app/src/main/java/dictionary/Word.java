package dictionary;

import java.util.ArrayList;
import java.util.List;

public class Word {
    public static final String CONTENT_BLOCK = "\t\t<idx:entry name=\"default\" scriptable=\"yes\" spell=\"yes\">\n\t\t\t"
            + "<h5><dt><idx:orth>%s</idx:orth></dt></h5>\n\t\t\t<dd>%s</dd>\n\t\t</idx:entry>\n\t\t<hr/>";

    private static final String INFLECTION = "<idx:iform value=\"%s\" /> ";
    private final String word;
    private final String definition;
    private final List<String> inflections = new ArrayList<>();

    public Word(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    public Word(String word, String definition, List<String> inflections) {
        this(word, definition);
        this.inflections.addAll(inflections);
    }

    public String getWord(){
        return word;
    }

    public String getWordHtml() {
        StringBuilder wordBuilder = new StringBuilder();
        wordBuilder.append(word);
        if(!inflections.isEmpty()){
            wordBuilder.append("\n\t\t\t\t<idx:infl>");
            for(String inflection : inflections){
                wordBuilder.append("\n\t\t\t\t\t");
                wordBuilder.append(String.format(INFLECTION, inflection));
            }
            wordBuilder.append("\n\t\t\t\t</idx:infl>\n\t\t\t");
        }
        return wordBuilder.toString();
    }

    public String getDefinition() {
        return definition;
    }

    public List<String> getInflections() {
        return inflections;
    }

    public String getBlock(){
        return String.format(CONTENT_BLOCK, getWordHtml(), getDefinition());
    }

}
