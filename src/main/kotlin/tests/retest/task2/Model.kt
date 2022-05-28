package tests.retest.task2

var currentTopIndex = 0
var currentLatestIndex = 0
fun getRandomText(): String {
    val loader = DataLoader()
    return loader.getRandomQuote()
}

fun getPopularText(): String {
    currentTopIndex++
    val loader = DataLoader()
    val allPopular = loader.getAllQuotes("http://bashorg.org/byrating")
    if (currentTopIndex >= allPopular.size) return "Больше нет цитат"
    return allPopular[currentTopIndex - 1]
}

fun getLatestText(): String {
    currentLatestIndex++
    val loader = DataLoader()
    val allLatest = loader.getAllQuotes("http://bashorg.org/")
    if (currentLatestIndex >= allLatest.size) return "Больше нет цитат"
    return allLatest[currentLatestIndex - 1]
}
