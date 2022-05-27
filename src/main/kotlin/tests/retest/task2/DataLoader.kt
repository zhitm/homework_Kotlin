package tests.retest.task2

import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class DataLoader {

    private fun getHtml(url: String): org.jsoup.nodes.Document? = runBlocking {
        return@runBlocking Jsoup.connect(url).get()
    }

    fun getRandomQuote(): String {
        val html = getHtml("http://bashorg.org/casual")
        val quoteWithTags = html?.select(".q div").toString()
        val splittedText = quoteWithTags.split("<div>", "</div>")
        val size = splittedText.size
        if (size - 2 < 0) return "Ошибка"
        return splittedText[size - 2].replace("<br>", "")
    }

    fun getAllQuotes(url: String): List<String> {
        val html = getHtml(url)
        val quotesAsHtml = html?.getElementsByClass("quote")
        return quotesAsHtml?.map {
            var quote = it.toString().replace("<br>", "")
            quote = quote.replace("<div class=\"quote\">", "")
            quote = quote.replace("</div>", "")
            quote
        } ?: emptyList()
    }
}
