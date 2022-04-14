package DictionaryBuilder;

import java.io.File;
import java.io.IOException;

public abstract class DictionaryPage {
    private File outputFile;

    public DictionaryPage(String outputPath){
        this.outputFile = new File(outputPath);
        this.outputFile.getParentFile().mkdirs();
    }

    public File getOutFile(){
        return outputFile;
    }

    public abstract void build() throws IOException;
}
