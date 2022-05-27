package dictionary

import java.io.IOException
import java.io.PrintWriter

/**
 * Very basic copy right page. Only there because Kindle requires it to be there.
 */
class CopyrightPage(outputPath: String) : DictionaryPage(outputPath + FILE_NAME) {
    @Throws(IOException::class)
    override fun build() {
        PrintWriter(outFile).use { writer -> writer.print(BASIC_CONTENT) }
    }

    companion object {
        const val FILE_NAME = "copyright.html"
        private const val BASIC_CONTENT =
            ("<html>\n\t<head>\n\t\t<meta content=\"text/html\" http-equiv=\"content-type\">\n\t</head>"
                    + "\n\t<body>\n\t\t<h1>Copyright page</h1>\n\t</body>\n</html>")
    }
}