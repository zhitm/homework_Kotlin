package tests.retest.task2

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class ViewModel {
    var state: State by mutableStateOf(initialState())
        private set

    private fun initialState(): State = State("")
    data class State(val quote: String)

    private inline fun updateState(update: State.() -> State) {
        state = state.update()
    }
    fun updateQuote(quote: String) = updateState { copy(quote = quote) }
}
