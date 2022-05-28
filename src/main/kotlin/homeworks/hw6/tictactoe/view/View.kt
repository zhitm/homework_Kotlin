package homeworks.hw6.tictactoe

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import homeworks.hw6.tictactoe.view.Board
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import homeworks.hw6.tictactoe.enums.FigureType
import homeworks.hw6.tictactoe.enums.GameType
import homeworks.hw6.tictactoe.enums.Screen
import homeworks.hw6.tictactoe.view.GameScreen
import homeworks.hw6.tictactoe.view.StartScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun view(viewModel: ViewModel) {
    val state = viewModel.state
    println(state.screen)
    MaterialTheme {
        when (state.screen) {
            Screen.StartScreen -> {
                StartScreen(viewModel)
            }
            Screen.GameScreen -> {
                GameScreen(viewModel)
            }
        }
    }
//    Column {
//        Text("За кого будет играть компьютер в следующей игре?")
//        GroupedRadioButton(listOf(FigureType.CROSS, FigureType.CIRCLE))
//        Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_YOURSELF));runWindow(viewModel) }) { Text("Сам с собой") }
//        Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_PC));runWindow(viewModel) }) { Text("Против компьютера (not ready yet)") }
//        println("view")
////        if (!game.isAwaitingStart()) {
////            Board(3, 3, viewModel)
////        }
////        if (game.isOver()) {
////            AlertDialog(onDismissRequest = {}, title = { Text("Игра окончена") },
////                text = { Text("В каком режиме дальше?") },
////                confirmButton = {
////                    Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_YOURSELF)) }) {
////                        Text(
////                            "Cам с собой"
////                        )
////                    }
////                },
////                dismissButton = { Button(onClick = { viewModel.updateState(startGame(GameType.AGAINST_PC)) }) { Text("Против компьютера") } })
////        }
//    }
}


