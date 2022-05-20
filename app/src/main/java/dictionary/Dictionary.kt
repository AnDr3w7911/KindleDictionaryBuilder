package dictionary

import java.io.FileNotFoundException
import java.io.IOException

class Dictionary private constructor(builder: DictionaryBuilder) {
    class DictionaryBuilder(private val outputPath: String) {
        val pages = arrayListOf<DictionaryPage>()

        @Throws(IOException::class)
        fun build(): Dictionary {
            return Dictionary(this)
        }

        fun cover(title: String, createdBy: String): DictionaryBuilder {
            pages.add(CoverPage(title, createdBy, outputPath))
            return this
        }

        @Throws(FileNotFoundException::class)
        fun content(contentPath: String): DictionaryBuilder {
            pages.add(ContentPage(outputPath, contentPath))
            return this
        }

        fun copyright(): DictionaryBuilder {
            pages.add(CopyrightPage(outputPath))
            return this
        }
    }

    private val pages: List<DictionaryPage>

    init {
        println("Creating Custom Kindle Dictionary...")
        pages = builder.pages
        if (pages.isNotEmpty()) {
            for (page in pages) {
                println("Creating: " + page.outFile.name)
                page.build()
            }
            println(
                "Dictionary files created: " + pages[0].outFile.parentFile.absolutePath
            )
        } else {
            println("No Files created.")
        }
    }
}