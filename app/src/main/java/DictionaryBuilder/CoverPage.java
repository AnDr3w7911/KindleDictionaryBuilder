package DictionaryBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CoverPage {
    private static final String FILE_NAME = "cover.html";
    private static final String COVER_PAGE = "<html>\n\t<head>\n\t\t<meta content=\"text/html\" http-equiv=\"content-type\">/n"
            + "\t</head>\n\t<body>\n\t\t<h1>%s</h1>\n\t\t<h3>Created by %s</h3>\n\t</body>\n</html>";

    public static void createCoverPage(String title, String createdBy, String outputPath) throws FileNotFoundException {
        File output = new File(outputPath + FILE_NAME);
        output.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(output)) {
            writer.print(String.format(COVER_PAGE, title, createdBy));
        }
    }
}
