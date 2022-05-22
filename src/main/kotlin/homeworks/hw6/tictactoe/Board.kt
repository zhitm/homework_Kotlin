package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.FigureType

class Board {
    private val field = Array(3) { IntArray(3) }
    fun makeMove(row: Int, column: Int, figure: FigureType): Boolean {
        require(row in 0..2 && column in 0..2) { "Попытка поставить фигуру вне поля" }
        if (field[row][column] != 0) return false
        field[row][column] = figure.index
        return true
    }
}