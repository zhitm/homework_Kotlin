package homeworks.hw6.tictactoe.PCgamer

import homeworks.hw6.tictactoe.Game
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType

class PCgamer(figureType: FigureType) {
    private val tree = TreeOfGames(Node(Game(GameType.AGAINST_PC, figureType)))
    fun makeMove(lastMove: Move?): Move {
        lastMove?.let { tree.changeStateByMove(it) }
        tree.printBoard()
        return tree.getBestMove()}
}
