package dictionary

import java.io.IOException
import java.io.PrintWriter

/**
 * Cover page of the dictionary.
 */
class CoverPage(private val title: String, private val createdBy: String, outputPath: String) :
    DictionaryPage(outputPath + FILE_NAME) {
    @Throws(IOException::class)
    override fun build() {
        PrintWriter(outFile).use { writer -> writer.print(String.format(COVER_PAGE, title, createdBy)) }
    }

    companion object {
        const val FILE_NAME = "cover.html"
        private const val COVER_PAGE =
            ("<html>\n\t<head>\n\t\t<meta content=\"text/html\" http-equiv=\"content-type\">\n"
                    + "\t</head>\n\t<body>\n\t\t<h1>%s</h1>\n\t\t<h3>Created by %s</h3>\n\t</body>\n</html>")
    }
}