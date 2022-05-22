package homeworks.hw6.tictactoe

import homeworks.hw6.tictactoe.enums.GameState

class Game {
    private var isCrossMove: Boolean = true
    private var state: GameState = GameState.AWAITING_START
    private val board = Board()
}