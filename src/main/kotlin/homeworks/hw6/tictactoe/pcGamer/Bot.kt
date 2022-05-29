package homeworks.hw6.tictactoe.pcGamer

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.game.Game

class Bot(private val figureType: FigureType) : DumbBot(figureType) {

    private val oppositionFigure
        get() = if (figureType == FigureType.CROSS) FigureType.CIRCLE else FigureType.CROSS

    override fun updateBotAndGetMove(lastMove: Move?): Move? {
        lastMove?.let { gameCopy.tryToMove(it.row, it.column) }
        val bestMove = getBestMove()
        bestMove?.let { gameCopy.tryToMove(bestMove.row, bestMove.column) }
        return bestMove
    }

    private fun getBestMove(): Move? {
        return getPossibleMoves(gameCopy).maxByOrNull { evalMove(it) }
    }

    @Suppress("ReturnCount")
    private fun evalMove(move: Move): Int {
        val state = getCopyAfterMove(gameCopy, move)
        var reward = 0
        println(state.winner)
        if (state.winner == figureType) return PC_WIN
        val oppositionMoves = getPossibleMoves(state)
        for (possibleMove in oppositionMoves) {
            if (getCopyAfterMove(state, possibleMove).winner == oppositionFigure) {
                reward += PC_LOOSE
            }
        }
        if (move.row == 1 && move.column == 1) {
            reward += CENTER
        }
        reward += goodLinesCount(state) * CAN_WIN_NEXT_MOVE
        println(reward)
        return reward
    }

    private fun goodLinesCount(game: Game): Int {
        var count = 0
        for (line in 0..2) {
            if (isGoodRow(game, line)) {
                count++
            }
            if (isGoodColumn(game, line)) {
                count++
            }
        }
        if (isGoodFirstDiagonal(game)) {
            count++
        }
        if (isGoodSecondDiagonal(game)) {
            count++
        }
        return count
    }

    private fun isGoodRow(game: Game, row: Int): Boolean {
        return game.board.field[row].count { it == figureType } == 2 &&
            game.board.field[row].count { it == oppositionFigure } == 0
    }

    private fun isGoodColumn(game: Game, column: Int): Boolean {
        val arrayOfFigures = (0..2).map { game.board.field[it][column] }
        return arrayOfFigures.count { it == figureType } == 2 && arrayOfFigures.count { it == oppositionFigure } == 0
    }

    private fun isGoodFirstDiagonal(game: Game): Boolean {
        val diagonal = (0..2).map { game.board.field[it][it] }
        return diagonal.count { it == figureType } == 2 && diagonal.count { it == oppositionFigure } == 0
    }

    private fun isGoodSecondDiagonal(game: Game): Boolean {
        val diagonal = (0..2).map { game.board.field[it][2 - it] }
        return diagonal.count { it == figureType } == 2 && diagonal.count { it == oppositionFigure } == 0
    }

    private fun getCopyAfterMove(game: Game, move: Move): Game {
        val newGame = game.copy()
        newGame.tryToMove(move.row, move.column)
        return newGame
    }

    companion object {
        const val PC_WIN = 100000
        const val PC_LOOSE = -100000
        const val CAN_WIN_NEXT_MOVE = 1000
        const val CENTER = 500
    }
}
