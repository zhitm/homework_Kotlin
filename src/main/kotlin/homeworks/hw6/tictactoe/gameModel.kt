package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.Complexity
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.game.Game
import homeworks.hw6.tictactoe.pcGamer.Bot
import homeworks.hw6.tictactoe.pcGamer.DumbBot

var game: Game = Game(GameType.AGAINST_PC, FigureType.CIRCLE)
var pcGamer = DumbBot(FigureType.CIRCLE)

fun startGame(complexity: Complexity, gameType: GameType, pcFigureType: FigureType) {
    pcGamer = when (complexity) {
        Complexity.EASY -> DumbBot(pcFigureType)
        Complexity.NORMAL -> Bot(pcFigureType)
    }
    game.startGame(gameType, pcFigureType)
}

fun makeMove(row: Int, column: Int) {
    game.makeMove(row, column)
}
