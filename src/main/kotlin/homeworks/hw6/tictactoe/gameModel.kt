package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.enums.Screen
import homeworks.hw6.tictactoe.game.Game
import homeworks.hw6.tictactoe.pcGamer.PCgamer

var game: Game = Game(GameType.AGAINST_PC, FigureType.CIRCLE)
var pcGamer = PCgamer(FigureType.CIRCLE)

fun startGame(gameType: GameType, pcFigureType: FigureType): ViewModel.State {
    pcGamer = PCgamer(pcFigureType)
    game.startGame(gameType, pcFigureType)
    return getState()
}

fun getState(): ViewModel.State = ViewModel.State(
    game.board,
    game.moveCount,
    game.pcFigureType,
    game.nextPlayer,
    game.state,
    game.gameType,
    Screen.GameScreen
)

fun makeMove(row: Int, column: Int): ViewModel.State {
    game.makeMove(row, column)
    return getState()
}
