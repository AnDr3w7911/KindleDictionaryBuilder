package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class CoverPage extends DictionaryPage {

    private static final String FILE_NAME = "cover.html";
    private static final String COVER_PAGE = "<html>\n\t<head>\n\t\t<meta content=\"text/html\" http-equiv=\"content-type\">/n"
            + "\t</head>\n\t<body>\n\t\t<h1>%s</h1>\n\t\t<h3>Created by %s</h3>\n\t</body>\n</html>";

    public static void createCoverPage(String title, String createdBy, File output) throws FileNotFoundException {
        try (PrintWriter writer = new PrintWriter(output)) {
            writer.print(String.format(COVER_PAGE, title, createdBy));
        }
    }

    private final String title;
    private final String createdBy;

    public CoverPage(String title, String createdBy, String outputPath) {
        super(outputPath + FILE_NAME);
        this.title = title;
        this.createdBy = createdBy;
    }

    @Override
    public void build() throws IOException {
        createCoverPage(title, createdBy, getOutFile());
    }
}
