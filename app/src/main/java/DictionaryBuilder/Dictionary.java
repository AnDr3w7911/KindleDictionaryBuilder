package DictionaryBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

    public static class Builder{
        private String outputPath;
        private List<DictionaryPage> pages;

        public Builder(String outputPath){
            this.outputPath = outputPath;
            pages = new ArrayList<>();
        }

        public Dictionary build(){
            return new Dictionary(this);
        }

        public Builder cover(String title, String createdBy){
            pages.add(new CoverPage(title, createdBy, outputPath));
            return this;
        }

        public Builder content(String contentPath) throws FileNotFoundException{
            pages.add(new Content(outputPath, contentPath));
            return this;
        }
    }

    private List<DictionaryPage> pages;
    

    private Dictionary(Builder builder){
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
