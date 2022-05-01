package homeworks.hw3

import homeworks.hw3.task1.AVLTreeMap
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class AVLTreeMapTest {
    @ParameterizedTest
    @MethodSource("dataForPutAndCheckCorrectness")
    fun `test put and correct`(entries: List<Pair<Int, Int>>) {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        entries.forEach { map[it.first] = it.second }
        assertEquals(true, map.isMapCorrect())
    }

    @ParameterizedTest
    @MethodSource("dataForPutAndCheckKeys")
    fun `test put and keys set`(entries: List<Pair<Int, Int>>, keys: Set<Int>) {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        entries.forEach { map[it.first] = it.second }
        assertEquals(map.keys, keys)
    }

    @ParameterizedTest
    @MethodSource("dataForPutAndCheckValues")
    fun `test put and values list`(entries: List<Pair<Int, Int>>, values: Set<Int>) {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        entries.forEach { map[it.first] = it.second }
        assertEquals(map.values, values)
    }

    @ParameterizedTest
    @MethodSource("dataForRemoveTest")
    fun `test remove`(entriesToPut: List<Pair<Int, Int>>, keysToRemove: List<Int>, keysLeft: Set<Int>) {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        entriesToPut.forEach { map[it.first] = it.second }
        keysToRemove.forEach {
            map.remove(it)
        }
        assertEquals(true, map.isMapCorrect() && keysLeft == map.keys)
    }

    @Test
    fun `test delete all`() {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        (1..1000).forEach { map[it] = it }
        (1..1000).forEach { map.remove(it) }
        assertEquals(true, map.isEmpty() && map.isMapCorrect())
    }

    @ParameterizedTest
    @MethodSource("dataForSizeTest")
    fun `test size`(entries: List<Pair<Int, Int>>, size: Int) {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        entries.forEach { map[it.first] = it.second }
        assertEquals(size, map.size)
    }

    @Test
    fun `test clear`() {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        (1..1000).forEach { map[it] = it }
        map.clear()
        assertEquals(true, map.isEmpty())
    }

    @Test
    fun `some test`() {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        map[1] = 1
        map[2] = 4
        map[3] = 4
        map.remove(3)
        map.remove(1)
        map.remove(10, 10)
        map.remove(12, 2)
        map[1] = 3
        assertEquals(true, map.isMapCorrect())
    }

    companion object {
        @JvmStatic
        fun dataForPutAndCheckCorrectness() = listOf(
            Arguments.of(List(500) { Pair(500 - it, 500 - it) }),
            Arguments.of(List(700) { Pair(it, it) }),
            Arguments.of(List(239) { Pair(it, it) }),
            Arguments.of(listOf(Pair(1, 1), Pair(2, 10), Pair(3, 100))),
            Arguments.of(listOf(Pair(1, 1), Pair(1, 10), Pair(1, 100))),
            Arguments.of(listOf(Pair(1, 1), Pair(2, 10))),
            Arguments.of(
                listOf(Pair(1, 1))
            )
        )

        @JvmStatic
        fun dataForPutAndCheckKeys() = listOf(
            Arguments.of(listOf(Pair(7, 1), Pair(2, 10), Pair(3, 100)), setOf(7, 2, 3)),
            Arguments.of(listOf(Pair(3, 1), Pair(1, 10), Pair(1, 100)), setOf(3, 1, 1)),
            Arguments.of(listOf(Pair(4, 4), Pair(8, 8)), setOf(4, 8)),
            Arguments.of(listOf(Pair(1, 1)), setOf(1))
        )

        @JvmStatic
        fun dataForPutAndCheckValues() = listOf(
            Arguments.of(listOf(Pair(7, 1), Pair(2, 10), Pair(3, 100)), setOf(1, 10, 100)),
            Arguments.of(listOf(Pair(3, 1), Pair(1, 9), Pair(1, 100)), setOf(1, 100)),
            Arguments.of(listOf(Pair(4, 4), Pair(8, 8)), setOf(4, 8)),
            Arguments.of(listOf(Pair(1, 1)), setOf(1)),
            Arguments.of(listOf<Pair<Int, Int>>(), setOf<Int>())
        )

        @JvmStatic
        fun dataForRemoveTest() = listOf(
            Arguments.of(List(100) { Pair(it, it) }, List(98) { it }, setOf(98, 99)),
            Arguments.of(listOf(Pair(2, 3), Pair(3, 4)), listOf(2), setOf(3))
        )

        @JvmStatic
        fun dataForSizeTest() = listOf(
            Arguments.of(List(100) { Pair(it, it) }, 100),
            Arguments.of(List(10) { Pair(it, it) }, 10),
            Arguments.of(List(0) { Pair(it, it) }, 0),
            Arguments.of(List(2) { Pair(it, it) }, 2),
        )
    }
}
