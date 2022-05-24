package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameState

class Game {
    var nextPlayer = FigureType.CROSS
        private set
    var state: GameState = GameState.AWAITING_START
        private set
    var board = Board()
        private set
    private var winner: FigureType? = null
    fun isOver(): Boolean = state == GameState.OVER
    fun isActive(): Boolean = state == GameState.ACTIVE
    fun isAwaitingStart(): Boolean = state == GameState.AWAITING_START
    fun startGame() {
        state = GameState.ACTIVE
        board = Board()
        nextPlayer = FigureType.CROSS
    }

    fun checkGameState() {
        checkWinInRow()
        checkWinInColumn()
        checkWinInDiagonal()
    }

    fun makeMove(row: Int, column: Int) {
        if (isActive()) {
            if (board.makeMove(row, column, nextPlayer)) {
                changePlayer()
                checkGameState()
            }
        }
    }

    private fun changePlayer() {
        nextPlayer = if (nextPlayer == FigureType.CROSS) FigureType.CIRCLE else FigureType.CROSS
    }

    private fun checkWinInRow() {
        for (row in 0..2) {
            if (board.field[row].contentEquals(Array(3) { FigureType.CROSS })) {
                winner = FigureType.CROSS
                overGame()
            } else if (board.field[row].contentEquals(Array(3) { FigureType.CIRCLE })) {
                winner = FigureType.CIRCLE
                overGame()
            }
        }
    }

    private fun checkWinInColumn() {
        for (row in 0..2) {
            if (board.field.all { it[row] == FigureType.CROSS }) {
                winner = FigureType.CROSS
                overGame()
            }
            if (board.field.all { it[row] == FigureType.CIRCLE }) {
                winner = FigureType.CIRCLE
                overGame()
            }
        }
    }

    private fun checkWinInDiagonal() {
        if ((0..2).all { board.field[it][it] == FigureType.CROSS }) {
            winner = FigureType.CROSS
            overGame()
        }
        if ((0..2).all { board.field[2 - it][it] == FigureType.CROSS }) {
            winner = FigureType.CROSS
            overGame()
        }
    }

    private fun overGame() {
        state = GameState.OVER
//        создать окошко и предлжить начать заново
    }
}
