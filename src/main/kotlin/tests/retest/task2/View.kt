package tests.retest.task2

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
@Preview
fun view(viewModel: ViewModel) {
    val state = viewModel.state
    val randomQuoteRequest by remember { mutableStateOf("Получить случайную цитату") }
    val bestQuoteRequest by remember { mutableStateOf("Получить следующую лучшую цитату") }
    val latestQuoteRequest by remember { mutableStateOf("Показать предыдущую цитату из последних по добавлению") }
    Column {
        MaterialTheme {
            Button(onClick = {
                viewModel.updateQuote(getRandomText())
            }) {
                Text(randomQuoteRequest)
            }
        }
        MaterialTheme {
            Button(onClick = {
                viewModel.updateQuote(getPopularText())
            }) {
                Text(bestQuoteRequest)
            }
        }
        MaterialTheme {
            Button(onClick = {
                viewModel.updateQuote(getLatestText())
            }) {
                Text(latestQuoteRequest)
            }
        }

        MaterialTheme {
            Text(state.quote)
        }
    }
}
