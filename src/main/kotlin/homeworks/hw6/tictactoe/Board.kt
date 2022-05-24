package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.FigureType

class Board {
    val field = Array(3) { Array(3) { FigureType.EMPTY } }
    fun makeMove(row: Int, column: Int, figure: FigureType): Boolean {
        require(row in 0..2 && column in 0..2) { "Попытка поставить фигуру вне поля" }
        require(figure != FigureType.EMPTY) { "Нельзя поставить пустую клетку" }
        if (field[row][column] != FigureType.EMPTY) return false
        field[row][column] = figure
        return true
    }
}
