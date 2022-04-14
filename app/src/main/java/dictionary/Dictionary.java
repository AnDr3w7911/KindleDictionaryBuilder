package dictionary;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    public static class DictionaryBuilder{
        private String outputPath;
        private List<DictionaryPage> pages;

        public DictionaryBuilder(String outputPath){
            this.outputPath = outputPath;
            pages = new ArrayList<>();
        }

        public Dictionary build(){
            return new Dictionary(this);
        }

        public DictionaryBuilder cover(String title, String createdBy){
            pages.add(new CoverPage(title, createdBy, outputPath));
            return this;
        }

        public DictionaryBuilder content(String contentPath) throws FileNotFoundException{
            pages.add(new Content(outputPath, contentPath));
            return this;
        }
    }

    private List<DictionaryPage> pages;
    

    private Dictionary(DictionaryBuilder builder){
        pages = builder.pages;
        try {
            for(var page : pages){
                page.build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }


}
