package dictionary

/**
 * A Word with its inflections and definition
 */
class Word(val word: String, val definition: String, val inflections: List<String> = ArrayList()) : Comparable<Word> {


    val block: String
        get() = String.format(CONTENT_BLOCK, createHtmlWord(), definition)
    fun createHtmlWord() : String {
            val wordBuilder = StringBuilder()
            wordBuilder.append(word)
            if (inflections.isNotEmpty()) {
                wordBuilder.append("\n\t\t\t\t<idx:infl>")
                for (inflection in inflections) {
                    wordBuilder.append("\n\t\t\t\t\t")
                    wordBuilder.append(String.format(INFLECTION, inflection))
                }
                wordBuilder.append("\n\t\t\t\t</idx:infl>\n\t\t\t")
            }
            return wordBuilder.toString()
        }
    companion object {
        const val CONTENT_BLOCK = ("\t\t<idx:entry name=\"default\" scriptable=\"yes\" spell=\"yes\">\n\t\t\t"
                + "<h5><dt><idx:orth>%s</idx:orth></dt></h5>\n\t\t\t<dd>%s</dd>\n\t\t</idx:entry>\n\t\t<hr/>")
        private const val INFLECTION = "<idx:iform value=\"%s\" /> "
    }

    override fun compareTo(other: Word): Int {
        return word.compareTo(other.word, true)
    }
}