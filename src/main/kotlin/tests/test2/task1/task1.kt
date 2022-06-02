package tests.test2.task1

fun main(){
//    val parser = Parser()
//    parser.getAllWikiUrls(
//        "https://ru.wikipedia.org/wiki/%D0%A4%D0%B0%D0%B9%D0%BB:Reichsadler_Deutsches_Reich_(1935%E2%80%931945).svg")

    val graph = Graph("https://en.wikipedia.org/wiki/Adolf_Hitler", "https://en.wikipedia.org/wiki/War_(disambiguation)", 5)
    println(graph.dfs())
}