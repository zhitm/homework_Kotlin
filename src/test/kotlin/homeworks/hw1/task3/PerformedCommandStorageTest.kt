package homeworks.hw1.task3

import homeworks.hw1.task3.actions.ChangePosition
import homeworks.hw1.task3.actions.Push
import homeworks.hw1.task3.actions.PushBack
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test

class PerformedCommandStorageTest {
    @ParameterizedTest
    @MethodSource("inputDataForPush")
    fun `test push and undo push`(storageInput: MutableList<Int>, expected: MutableList<Int>, value: Int) {
        val storage = PerformedCommandStorage(storageInput)
        storage.addCommand(Push(value))
        assertEquals(expected, storage.currentState)
        storage.undoLastCommand()
        assertEquals(storageInput, storage.currentState)
    }

    @ParameterizedTest
    @MethodSource("inputDataForPushBack")
    fun `test pushBack and undo pushBack`(storageInput: MutableList<Int>, expected: MutableList<Int>, value: Int) {
        val storage = PerformedCommandStorage(storageInput)
        storage.addCommand(PushBack(value))
        assertEquals(expected, storage.currentState)
        storage.undoLastCommand()
        assertEquals(storageInput, storage.currentState)
    }

    @Test
    fun `test undo all commands`() {
        val storage = PerformedCommandStorage(mutableListOf(1))
        storage.addCommand(PushBack(2))
        storage.addCommand(Push(2))
        storage.addCommand(ChangePosition(0, 1))
        storage.undoLastCommand()
        storage.undoLastCommand()
        storage.undoLastCommand()
        assertEquals(mutableListOf(1), storage.currentState)
    }

    @ParameterizedTest
    @MethodSource("inputDataForChangePosition")
    fun `test changePosition and undo changePosition`(
        storageInput: MutableList<Int>,
        expected: MutableList<Int>,
        fromIndex: Int,
        toIndex: Int
    ) {
        val storage = PerformedCommandStorage(storageInput)
        storage.addCommand(ChangePosition(fromIndex, toIndex))
        assertEquals(expected, storage.currentState)
        storage.undoLastCommand()
        assertEquals(storageInput, storage.currentState)
    }

    companion object {
        @JvmStatic
        fun inputDataForPush() = listOf(
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(0, 1, 2, 3, 4), 0),
            Arguments.of(mutableListOf<Int>(), mutableListOf(1), 1),
            Arguments.of(mutableListOf(1), mutableListOf(100, 1), 100),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1), mutableListOf(100, 1, 1, 1, 1, 1), 100),
        )

        @JvmStatic
        fun inputDataForPushBack() = listOf(
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(1, 2, 3, 4, 0), 0),
            Arguments.of(mutableListOf<Int>(), mutableListOf(1), 1),
            Arguments.of(mutableListOf(1), mutableListOf(1, 100), 100),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1), mutableListOf(1, 1, 1, 1, 1, 100), 100),
        )

        @JvmStatic
        fun inputDataForChangePosition() = listOf(
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(2, 3, 4, 1), 0, 3),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(4, 1, 2, 3), 3, 0),
            Arguments.of(mutableListOf(1), mutableListOf(1), 0, 0),
            Arguments.of(mutableListOf(1, 1, 1, 1, 1), mutableListOf(1, 1, 1, 1, 1), 1, 3),
            Arguments.of(mutableListOf(1, 2, 3, 4), mutableListOf(1, 3, 4, 2), 1, 3)
        )
    }
}
