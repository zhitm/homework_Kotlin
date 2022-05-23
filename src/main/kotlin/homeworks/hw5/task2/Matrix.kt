package homeworks.hw5.task2

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Matrix(private val table: Array<IntArray>) {
    init {
        require(table.isNotEmpty()) { "Матрица не должна быть пуста" }
        require(table[0].isNotEmpty()) { "Матрица не должна быть пуста" }
        require(table.all { it.size == table[0].size }) { "Все строки должны быть одного размера" }
    }

    private val height = table.size
    private val width = table[0].size

    constructor(height: Int, width: Int) : this(Array(height) { IntArray(width) { 0 } })

    private fun getColumn(index: Int): IntArray {
        val column = IntArray(height) { 0 }
        (0 until height).forEach { column[it] = table[it][index] }
        return column
    }

    fun setRow(row: IntArray, index: Int) {
        table[index] = row
    }

    private fun scalar(row: Int, column: Int, anotherMatrix: Matrix, resultMatrix: Matrix) {
        val array1 = table[row]
        val array2 = anotherMatrix.getColumn(column)
        resultMatrix.table[row][column] = (array1.indices.map { array1[it] * array2[it] }).sum()
    }

    operator fun times(anotherMatrix: Matrix): Matrix {
        require(width == anotherMatrix.height) { "Эти матрицы нельзя перемножить" }
        val resultMatrix = Matrix(height, anotherMatrix.width)
        runBlocking {
            val jobs = mutableListOf<Deferred<Unit>>()
            for (row in 0 until height) {
                for (column in 0 until anotherMatrix.width) {
                    jobs.add(async { scalar(row, column, anotherMatrix, resultMatrix) })
                }
            }
            jobs.forEach { it.await() }
        }
        return resultMatrix
    }

    fun fillByRandom(min: Int, max: Int) {
        for (row in 0 until height) {
            for (column in 0 until width) {
                table[row][column] = (min..max).random()
            }
        }
    }

    private fun rowToString(row: IntArray): String {
        var str = " "
        row.forEach { str += "$it " }
        str += "\n"
        return str
    }

    override fun toString(): String {
        var str = " "
        table.forEach { str += rowToString(it) }
        return str
    }
}
