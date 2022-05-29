package homeworks.hw6.tictactoe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.Complexity
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType

@Suppress("FunctionNaming")
@Composable
fun StartScreen(viewModel: ViewModel) {
    Column {
        Text("Выберите тип игры:")
        GroupRadioButton(
            mapOf(GameType.AGAINST_PC to "Против компьютера", GameType.AGAINST_YOURSELF to "Сам с собой"),
            GameType.AGAINST_YOURSELF,
            viewModel::onGameTypeSelect
        )
        if (viewModel.state.gameType == GameType.AGAINST_PC) {
            Text("За кого будет играть компьютер в следующей игре?")
            GroupRadioButton(
                mapOf(FigureType.CROSS to "Крестики", FigureType.CIRCLE to "Нолики"),
                FigureType.CIRCLE,
                viewModel::onSideSelect
            )
            Text("Выберите уровень сложности:")
            GroupRadioButton(
                mapOf(Complexity.EASY to "Простой", Complexity.NORMAL to "Нормальный"),
                Complexity.NORMAL,
                viewModel::onComplexitySelect
            )
        }
        Button(onClick = {
            viewModel.onStart()
        }) { Text("Начать игру") }
    }
}
