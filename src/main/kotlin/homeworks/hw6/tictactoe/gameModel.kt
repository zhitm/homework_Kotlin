package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.PCgamer.PCgamer
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.enums.Screen
import homeworks.hw6.tictactoe.game.Game

var game: Game = Game(GameType.AGAINST_PC, FigureType.CIRCLE)
var pcGamer = PCgamer(FigureType.CIRCLE)
//fix pcGame as cross!~!!!!!!!!!!!!!!!!!
fun startGame(gameType: GameType): ViewModel.State {
    game.startGame(gameType)
    pcGamer = PCgamer(FigureType.CIRCLE)
    return getState()
}

//!!!!!!!!!!!!!!!!!!!!!!! screen
fun getState(): ViewModel.State = ViewModel.State(
    game.board,
    game.moveCount,
    game.PCFigure,
    game.nextPlayer,
    game.state,
    game.gameType,
    Screen.GameScreen
)

fun makeMove(row: Int, column: Int): ViewModel.State {
    game.makeMove(row, column)
    return getState()
}
