package dictionary

import java.io.FileNotFoundException
import java.io.IOException

/**
 *  Kindle Custom Dictionary pages
 */
class Dictionary private constructor(builder: DictionaryBuilder) {
    /**
     * Builder to create the pages of a custom kindle dictionary
     * @param outputPath path to output the created dictionary pages
     */
    class DictionaryBuilder(private val outputPath: String) {
        private val _pages = arrayListOf<DictionaryPage>()

        val pages: List<DictionaryPage>
            get() = _pages

        @Throws(IOException::class)
        fun build(): Dictionary {
            return Dictionary(this)
        }

        fun cover(title: String, createdBy: String): DictionaryBuilder {
            _pages.add(CoverPage(title, createdBy, outputPath))
            return this
        }

        @Throws(FileNotFoundException::class)
        fun content(contentPath: String): DictionaryBuilder {
            _pages.add(ContentPage(outputPath, contentPath))
            return this
        }

        fun copyright(): DictionaryBuilder {
            _pages.add(CopyrightPage(outputPath))
            return this
        }
    }

    private val pages = builder.pages

    init {
        println("Creating Custom Kindle Dictionary...")
        if (pages.isNotEmpty()) {
            pages.forEach {
                println("Creating: ${it.outFile.name}")
                it.build()
            }
            println(
                "Dictionary files created: ${pages[0].outFile.parentFile.absolutePath}"
            )
        } else {
            println("No Files created.")
        }
    }
}