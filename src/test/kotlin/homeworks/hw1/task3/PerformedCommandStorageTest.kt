package homeworks.hw1.task3

import homeworks.hw1.task3.actions.ChangePosition
import homeworks.hw1.task3.actions.Push
import homeworks.hw1.task3.actions.PushBack
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals

class PerformedCommandStorageTest {
    private val push = Push
    private val pushBack = PushBack
    private val changePosition = ChangePosition

    @ParameterizedTest
    @MethodSource("inputDataForPush")
    fun `test push and undo push`(storageInput: MutableList<Int>, expected: MutableList<Int>, args: IntArray) {
        val storage = PerformedCommandStorage(storageInput)
        storage.addCommand(push, args)
        assertEquals(expected, storage.getList())
        storage.undoCommand()
        assertEquals(storageInput, storage.getList())
    }

    @ParameterizedTest
    @MethodSource("inputDataForPushBack")
    fun `test pushBack and undo pushBack`(storageInput: MutableList<Int>, expected: MutableList<Int>, args: IntArray) {
        val storage = PerformedCommandStorage(storageInput)
        storage.addCommand(pushBack, args)
        assertEquals(expected, storage.getList())
        storage.undoCommand()
        assertEquals(storageInput, storage.getList())
    }

    @Test
    fun `test commands list is empty after undo all commands`() {
        val storage = PerformedCommandStorage(mutableListOf(1))
        storage.addCommand(pushBack, intArrayOf(2))
        storage.addCommand(push, intArrayOf(2))
        storage.addCommand(changePosition, intArrayOf(0, 1))
        storage.undoCommand()
        storage.undoCommand()
        storage.undoCommand()
        assertEquals(mutableListOf(1), storage.getList())
        assertEquals(0, storage.commandCounter)
    }

    @ParameterizedTest
    @MethodSource("inputDataForChangePosition")
    fun `test changePosition and undo changePosition`(
        storageInput: MutableList<Int>,
        expected: MutableList<Int>,
        args: IntArray
    ) {
        val storage = PerformedCommandStorage(storageInput)
        storage.addCommand(changePosition, args)
        assertEquals(expected, storage.getList())
        storage.undoCommand()
        assertEquals(storageInput, storage.getList())
    }

    companion object {
        @JvmStatic
        fun inputDataForPush() = listOf(
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(0, 1, 2, 3, 4), intArrayOf(0)),
            Arguments.of(mutableListOf<Int>(), mutableListOf(1), intArrayOf(1)),
            Arguments.of(mutableListOf(1), mutableListOf(100, 1), intArrayOf(100)),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1), mutableListOf(100, 1, 1, 1, 1, 1), intArrayOf(100)),
        )

        @JvmStatic
        fun inputDataForPushBack() = listOf(
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(1, 2, 3, 4, 0), intArrayOf(0)),
            Arguments.of(mutableListOf<Int>(), mutableListOf(1), intArrayOf(1)),
            Arguments.of(mutableListOf(1), mutableListOf(1, 100), intArrayOf(100)),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1), mutableListOf(1, 1, 1, 1, 1, 100), intArrayOf(100)),
        )

        @JvmStatic
        fun inputDataForChangePosition() = listOf(
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(2, 3, 4, 1), intArrayOf(0, 3)),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(4, 1, 2, 3), intArrayOf(3, 0)),
            Arguments.of(mutableListOf(1), mutableListOf(1), intArrayOf(0, 0)),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1), mutableListOf(1, 1, 1, 1, 1), intArrayOf(1, 3)),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(1, 3, 4, 2), intArrayOf(1, 3))
        )
    }
}