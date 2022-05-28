package homeworks.hw6.tictactoe.game

import homeworks.hw6.tictactoe.enums.FigureType

class Board {
    var field = Array(SIZE) { Array(SIZE) { FigureType.EMPTY } }
    fun makeMove(row: Int, column: Int, figure: FigureType): Boolean {
        require(row in 0 until SIZE && column in 0 until SIZE) { "Попытка поставить фигуру вне поля" }
        require(figure != FigureType.EMPTY) { "Нельзя поставить пустую клетку" }
        if (field[row][column] != FigureType.EMPTY) return false
        field[row][column] = figure
        return true
    }

    fun printBoard() {
        field.forEach { row ->
            row.forEach { print("$it ") }
            println()
        }
    }

    fun copy(): Board {
        val boardCopy = Board()
        boardCopy.field[0] = field[0].clone()
        boardCopy.field[1] = field[1].clone()
        boardCopy.field[2] = field[2].clone()

        return boardCopy
    }
    companion object {
        const val SIZE = 3
    }
}
