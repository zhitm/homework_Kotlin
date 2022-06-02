package tests.test2.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertNotNull

class ParserTest {
    private val parser = Parser()

    @ParameterizedTest
    @MethodSource("urls")
    fun testUrls(url: String, isCorrect: Boolean) {
        assertEquals(parser.isUrlValid(url), isCorrect)
    }

    @Test
    fun getUrls() {
        assertNotNull(parser.getAllWikiUrls("https://en.wikipedia.org/wiki/War_(disambiguation)"))
    }

    companion object {
        @JvmStatic
        fun urls() = listOf(
            Arguments.of("тырпыр 8 дыр", false),
            Arguments.of("", false),
            Arguments.of("wikiwikidfbafbdfbsdfs", false),
            Arguments.of("https://en.wikipedia.org/wiki/AVL_tree", true),
            Arguments.of("https://en.wikipedia.org/wiki/War_(disambiguation)", true)
        )
    }
}
