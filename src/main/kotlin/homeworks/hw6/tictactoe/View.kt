package homeworks.hw6.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.view.Board
import androidx.compose.material.Text
import homeworks.hw6.tictactoe.enums.GameType

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview
fun view(viewModel: ViewModel) {
    Column {
        Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_YOURSELF)) }) { Text("Сам с собой") }
        Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_PC)) }) { Text("Против компьютера (not ready yet)") }

        if (!game.isAwaitingStart()) {
            Board(3, 3, viewModel)
        }
        if (game.isOver()) {
            AlertDialog(onDismissRequest = { println(1) }, title = { Text("Игра окончена") },
                text = { Text("В каком режиме дальше?") },
                confirmButton = {
                    Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_YOURSELF)) }) {
                        Text(
                            "Cам с собой"
                        )
                    }
                },
                dismissButton = { Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_PC)) }) { Text("Против компьютера") } })
        }
    }
}