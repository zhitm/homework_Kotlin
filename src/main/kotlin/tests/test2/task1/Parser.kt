package tests.test2.task1

import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class Parser {
    private fun getHtml(url: String): org.jsoup.nodes.Document? = runBlocking {
        return@runBlocking Jsoup.connect(url).get()
    }

    @Suppress("SwallowedException")
    fun isUrlValid(url: String): Boolean {
        try {
            Jsoup.connect(url).get()
        } catch (e: java.lang.IllegalArgumentException) {
            System.err.println("Ссылка некорректна")
            return false
        }
        return true
    }

    fun getAllWikiUrls(url: String): List<String> {
        val document = getHtml(url)
        val prefix = url.split("/wiki")[0]
        val links = document?.select("#bodyContent div:not(#mw-panel) a[href^=/wiki/]")
        return links?.map { prefix + it.attr("href") } ?: emptyList()
    }
}
