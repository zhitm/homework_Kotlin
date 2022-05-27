
package tests.retest.task2

import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class TestParser {

    @ParameterizedTest
    @MethodSource("dataForParserTest")
    fun `test parser on top quote`(quote: String) {
        assertEquals(quote, getPopularText())
    }

    @RepeatedTest(10)
    fun `test not null on random`() {
        assertNotNull(getRandomText())
    }

    @RepeatedTest(10)
    fun `test not null on latest`() {
        assertNotNull(getLatestText())
    }

    @RepeatedTest(10)
    fun `test not null on top`() {
        assertNotNull(getPopularText())
    }
    @Suppress("MaxLineLength")
    companion object {
        @JvmStatic
        fun dataForParserTest() = listOf(
            Arguments.of(
                """
 Svettik: приветик, можешь мне пожалуйста лабораторную помочь решить 
 Jon: првет...хочешь всю правду?
 Svettik: какую правду
 Jon: во 1 - когда ты говоришь помочь - это нихуя не помочь, а полностью решить, и еще начертить блок схему и обяснить как всё это работает
 во 2 - уже на протяжении 3 месяцев как я с тобой знаком, решаю тебе каждую неделю по несколько лаб, задач и тд. Я часто ахуевал с этих задач, наверно потому что ты учишься в вышке и там такой уровень, а я в средухе.... Но я запрягал друзей, когда сам не мог разобратся в коде, писал на форумах, спамил в хабе даже,...бывало тратил несколько часов, сидел весь вечер, понимая что ты в этом нихуя не шаришь и тока на меня вся надежда. ПОтом я торчал(и сейчас даже до сих пор торчу) тем кто мне помог или ты хули думала всё так просто за спасибо? Да, ты мне всегда говарила огромное спасибо, смайлики там ставила и всякую хуйню. ДА, зачет по вышмату у тебя автоматом...зачет по дискретки у тебя автоматом...всё заебись я рад за тебя честно. Я конечно не требовал чтоб ты мне дала за эту всю помощь, ты же приличная девушка. Но блять даже не было при встречах накакой награды там или намека...просто блять как друзья...
 Svettik: У тебя неудачный день выдался?
 Jon: нет всё заебись, только решай сама свои ебнутые задачи 
"""
            )
        )
    }
}
