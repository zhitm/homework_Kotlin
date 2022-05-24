package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.PCgamer.Move
import homeworks.hw6.tictactoe.PCgamer.Node
import homeworks.hw6.tictactoe.PCgamer.TreeOfGames
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameState
import homeworks.hw6.tictactoe.enums.GameType

class Game(var gameType: GameType, var PCFigure: FigureType = FigureType.CIRCLE) {
//    private val tree = TreeOfGames(Node(this))

    private val gamerFigure = if (PCFigure == FigureType.CIRCLE) FigureType.CROSS else FigureType.CIRCLE
    var isAwaitingPC = PCFigure != FigureType.CIRCLE
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

    fun startGame(gameType: GameType, PCFigure: FigureType ) {
        this.PCFigure = PCFigure
//        добавить выбор крестиков для пк
        this.gameType = gameType
        state = GameState.ACTIVE
        board = Board()
        nextPlayer = FigureType.CROSS
    }

    private fun checkGameState() {
        checkWinInRow()
        checkWinInColumn()
        checkWinInDiagonal()
        checkNoEmptyCells()
    }

    private fun tryToMove(row: Int, column: Int) {
        if (board.makeMove(row, column, nextPlayer)) {
            changePlayer()
            checkGameState()
        }
    }

    fun makeMove(row: Int, column: Int) {
        if (isActive()) {
            when (gameType) {
                GameType.AGAINST_YOURSELF -> tryToMove(row, column)
                GameType.AGAINST_PC -> {
                    if (isAwaitingPC) return
                    if (gamerFigure == nextPlayer) {
                        makeMove(row, column)
//                        tree.changeRootByMove(Move(row, column, gamerFigure))
                    }
                    else {
//                        ход компухтера
                        isAwaitingPC = true
//                        val bestMove = tree.getBestMove()
//                        tryToMove(bestMove.row,bestMove.column)
                        isAwaitingPC = false
                    }
                }
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

    private fun checkNoEmptyCells() {
        board.field.forEach { row -> row.forEach { if (it == FigureType.EMPTY) return } }
        overGame()
    }

    private fun overGame() {
        state = GameState.OVER
    }

    fun copy(): Game {
        val gameCopy = Game(gameType, PCFigure)
        gameCopy.board = board
        gameCopy.state = state
        gameCopy.nextPlayer = nextPlayer
        return gameCopy
    }

}
