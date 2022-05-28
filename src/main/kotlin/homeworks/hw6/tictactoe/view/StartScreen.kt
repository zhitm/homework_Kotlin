package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.PCgamer.GroupRadioButton
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.startGame

@Composable
fun StartScreen(viewModel: ViewModel) {
    Column {
        Text("Выберите тип игры:")
        GroupRadioButton(
            listOf(GameType.AGAINST_PC, GameType.AGAINST_YOURSELF),
            GameType.AGAINST_YOURSELF,
            viewModel::onGameTypeSelect
        )
        if (viewModel.state.gameType == GameType.AGAINST_PC) {
            Text("За кого будет играть компьютер в следующей игре?")
            GroupRadioButton(listOf(FigureType.CROSS, FigureType.CIRCLE), FigureType.CIRCLE, viewModel::onSideSelect)
        }
        Button(onClick = { viewModel.updateState(startGame(viewModel.state.gameType)) }) { Text("Начать игру") }

//        Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_YOURSELF)) }) { Text("Сам с собой") }
//        Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_PC)) }) { Text("Против компьютера (not ready yet)") }
//        println("view")
    }
}