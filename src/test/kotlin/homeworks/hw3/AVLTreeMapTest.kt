package homeworks.hw3

import homeworks.hw3.task1.AVLTreeMap
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AVLTreeMapTest {
    @Test
    fun `test add`() {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        map.add(1, 1)
        map.add(2, 2)
        map.add(3, 4)
        assertEquals(true, map.isMapCorrect())
    }

    @Test
    fun `test delete`() {
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        map.add(1, 1)
        map.add(2, 2)
        map.add(3, 4)
        map.add(-10, 1)
        map.add(4, 4)
        map.add(-2, 1)
        map.add(10, 1)
        map.delete(3)
        map.delete(-10)
        map.delete(1)
        assertEquals(true, map.isMapCorrect())
    }
    @Test
    fun `test delete all`(){
        val map: AVLTreeMap<Int, Int> = AVLTreeMap()
        map.add(1, 1)
        map.add(2, 2)
        map.add(3, 4)
        map.add(-10, 1)
        map.add(4, 4)
        map.add(-2, 1)
        map.add(10, 1)
        map.delete(1)
        map.delete(2)
        map.delete(3)
        map.delete(-10)
        map.delete(4)
        map.delete(-2)
        map.delete(10)
        assertEquals(true, map.isEmpty())
    }


}
