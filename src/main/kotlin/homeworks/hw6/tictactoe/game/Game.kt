package homeworks.hw6.tictactoe.game

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameState
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.pcGamer
import homeworks.hw6.tictactoe.pcGamer.Move

@Suppress("TooManyFunctions")
class Game(var gameType: GameType, var pcFigureType: FigureType = FigureType.EMPTY) {
    var moveCount = 0

    private var gamerFigure = if (pcFigureType == FigureType.CIRCLE) FigureType.CROSS else FigureType.CIRCLE

    var nextPlayer = FigureType.CROSS
        private set

    var state: GameState = GameState.AWAITING_START
        private set
    var board = Board()
        private set
    var winner: FigureType = FigureType.EMPTY

    fun isOver(): Boolean = state == GameState.OVER
    fun isActive(): Boolean = state == GameState.ACTIVE
    fun isAwaitingStart(): Boolean = state == GameState.AWAITING_START

    fun startGame(gameType: GameType, pcFigure: FigureType) {
        this.pcFigureType = pcFigure
        this.gameType = gameType
        gamerFigure = if (pcFigureType == FigureType.CIRCLE) FigureType.CROSS else FigureType.CIRCLE
        state = GameState.ACTIVE
        board = Board()
        nextPlayer = FigureType.CROSS
        if (gameType == GameType.AGAINST_PC && pcFigureType == FigureType.CROSS) {
            pcMove(null)
        }
    }

    private fun checkGameState() {
        checkWinInRow()
        checkWinInColumn()
        checkWinInDiagonal()
        checkNoEmptyCells()
    }

    fun tryToMove(row: Int, column: Int) {
        if (board.makeMove(row, column, nextPlayer)) {
            changePlayer()
            checkGameState()
            moveCount++
        }
    }

    private fun pcMove(lastMove: Move?) {
        val move = pcGamer.updateBotAndGetMove(lastMove)
        move?.let { board.makeMove(move.row, move.column, move.gamer) }
        moveCount++
        changePlayer()
        checkGameState()
    }

    fun sendMoveToPC(row: Int, column: Int) {
        if (gamerFigure == nextPlayer) {
            tryToMove(row, column)
            if (pcFigureType == nextPlayer) {
                pcMove(Move(row, column, gamerFigure))
            }
        }
    }

    fun makeMove(row: Int, column: Int) {
        if (isActive()) {
            when (gameType) {
                GameType.AGAINST_YOURSELF -> tryToMove(row, column)
                GameType.AGAINST_PC -> sendMoveToPC(row, column)
            }
        }
    }

    private fun changePlayer() {
        nextPlayer = if (nextPlayer == FigureType.CROSS) FigureType.CIRCLE else FigureType.CROSS
    }

    private fun checkWinInRow() {
        for (row in 0..2) {
            if (board.field[row].contentEquals(Array(SIZE) { FigureType.CROSS })) {
                winner = FigureType.CROSS
                overGame()
            } else if (board.field[row].contentEquals(Array(SIZE) { FigureType.CIRCLE })) {
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
        if ((0..2).all { board.field[it][it] == FigureType.CIRCLE }) {
            winner = FigureType.CIRCLE
            overGame()
        }
        if ((0..2).all { board.field[2 - it][it] == FigureType.CIRCLE }) {
            winner = FigureType.CIRCLE
            overGame()
        }
    }

    private fun checkNoEmptyCells() {
        board.field.forEach { row -> row.forEach { if (it == FigureType.EMPTY) return } }
        overGame()
    }

    private fun overGame() {
        state = GameState.OVER
    }

    fun copy(): Game {
        val gameCopy = Game(gameType, pcFigureType)
        gameCopy.board = board.copy()
        gameCopy.state = state
        gameCopy.nextPlayer = nextPlayer
        return gameCopy
    }

    companion object {
        const val SIZE = 3
    }
}
