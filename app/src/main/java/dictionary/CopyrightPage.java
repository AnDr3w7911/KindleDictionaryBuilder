package dictionary;

import java.io.IOException;
import java.io.PrintWriter;

public class CopyrightPage extends DictionaryPage {
    public static final String FILE_NAME = "copyright.html";

    private static final String BASIC_CONTENT = "<html>\n\t<head>\n\t\t<meta content=\"text/html\" http-equiv=\"content-type\">\n\t</head>"
            + "\n\t<body>\n\t\t<h1>Copyright page</h1>\n\t</body>\n</html>";

    public CopyrightPage(String outputPath) {
        super(outputPath + FILE_NAME);
    }

    @Override
    public void build() throws IOException {
        try (PrintWriter writer = new PrintWriter(getOutFile())) {
            writer.print(BASIC_CONTENT);
        }
    }
}
