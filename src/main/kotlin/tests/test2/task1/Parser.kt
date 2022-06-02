package tests.test2.task1

import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup

class Parser {
    private fun getHtml(url: String): org.jsoup.nodes.Document? = runBlocking {
        return@runBlocking Jsoup.connect(url).get()
    }

    fun getAllWikiUrls(url: String): List<String>{
        val document = getHtml(url)
        val prefix = url.split("/wiki")[0]
        val links = document?.select("#bodyContent div:not(#mw-panel) a[href^=/wiki/]")
        if (links != null) {
            println(links.size)
        }
//        links?.forEach { println(it)}
        return links?.map{ prefix+it.attr("href")} ?: emptyList()
    }
}