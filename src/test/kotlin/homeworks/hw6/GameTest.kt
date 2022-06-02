package homeworks.hw6

import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.game.Game
import homeworks.hw6.tictactoe.pcGamer.Bot
import homeworks.hw6.tictactoe.pcGamer.DumbBot
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class GameTest {
    private val game = Game(GameType.AGAINST_YOURSELF)

    @ParameterizedTest
    @MethodSource("dataForTestWin")
    fun `test win`(moves: List<Coord>, winner: FigureType) {
        game.startGame(GameType.AGAINST_YOURSELF, FigureType.EMPTY)
        moves.forEach { move -> game.makeMove(move.row, move.column) }
        assertEquals(true, game.winner == winner)
    }

    @ParameterizedTest
    @MethodSource("dataForTestMove")
    fun `test single moves`(move: Coord, expected: List<List<FigureType>>) {
        game.startGame(GameType.AGAINST_YOURSELF, FigureType.EMPTY)
        game.makeMove(move.row, move.column)
        assertEquals(expected, game.board.field.map { it.toList() })
    }

    @ParameterizedTest
    @MethodSource("dataForTestMoves")
    fun `test multiple moves`(moves: List<Coord>, expected: List<List<FigureType>>) {
        game.startGame(GameType.AGAINST_YOURSELF, FigureType.EMPTY)
        moves.forEach { move -> game.makeMove(move.row, move.column) }
        assertEquals(expected, game.board.field.map { it.toList() })
    }

    @Test
    fun `test dumb bot`() {
        game.startGame(GameType.AGAINST_PC, FigureType.CROSS)
        val bot = DumbBot(FigureType.CROSS)
        assertNotNull(bot.updateBotAndGetMove(null))
    }

    @Test
    fun `test bot`() {
        game.startGame(GameType.AGAINST_PC, FigureType.CROSS)
        val bot = Bot(FigureType.CROSS)
        assertNotNull(bot.updateBotAndGetMove(null))
    }

    companion object {
        @JvmStatic
        fun dataForTestMove() = listOf(
            Arguments.of(
                Coord(0, 0), listOf(
                    listOf(FigureType.CROSS, FigureType.EMPTY, FigureType.EMPTY),
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY),
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY)
                )
            ),
            Arguments.of(
                Coord(1, 1), listOf(
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY),
                    listOf(FigureType.EMPTY, FigureType.CROSS, FigureType.EMPTY),
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY)
                )
            )
        )

        @JvmStatic
        fun dataForTestMoves() = listOf(
            Arguments.of(
                listOf(Coord(0, 0), Coord(1, 0), Coord(1, 1), Coord(1, 2)),
                listOf(
                    listOf(FigureType.CROSS, FigureType.EMPTY, FigureType.EMPTY),
                    listOf(FigureType.CIRCLE, FigureType.CROSS, FigureType.CIRCLE),
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY)
                )
            ),
            Arguments.of(
                listOf(Coord(0, 0), Coord(0, 0), Coord(0, 0), Coord(0, 0)),
                listOf(
                    listOf(FigureType.CROSS, FigureType.EMPTY, FigureType.EMPTY),
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY),
                    listOf(FigureType.EMPTY, FigureType.EMPTY, FigureType.EMPTY)
                )
            )
        )

        @JvmStatic
        fun dataForTestWin() = listOf(
            Arguments.of(emptyList<Coord>(), FigureType.EMPTY),
            Arguments.of(listOf(Coord(0, 0), Coord(1, 0), Coord(1, 1), Coord(2, 0), Coord(2, 2)), FigureType.CROSS),
            Arguments.of(
                listOf(Coord(0, 2), Coord(0, 0), Coord(1, 0), Coord(1, 1), Coord(2, 0), Coord(2, 2)),
                FigureType.CIRCLE
            )
        )
    }
}
