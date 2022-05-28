package homeworks.hw6.tictactoe.view

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import homeworks.hw6.tictactoe.ViewModel
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.enums.Screen
import homeworks.hw6.tictactoe.game
import homeworks.hw6.tictactoe.startGame
import kotlin.system.exitProcess

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun GameScreen(viewModel: ViewModel) {
    viewModel.state
    if (!game.isAwaitingStart()) {
        Board(3, 3, viewModel)
    }
    if (game.isOver()) {
        AlertDialog(onDismissRequest = {}, title = { Text("Игра окончена") },
            text = { Text("В каком режиме дальше?") },
            confirmButton = {
                Button(onClick = { viewModel.updateScreen(Screen.StartScreen) })
                { Text("Начать новую игру") }
            },
            dismissButton = {
                Button(onClick = { exitProcess(0) })
                { Text("Выйти из игры") }
            })
    }
}