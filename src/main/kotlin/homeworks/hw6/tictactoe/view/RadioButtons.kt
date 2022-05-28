package homeworks.hw6.tictactoe.PCgamer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun <T> GroupRadioButton(mItems: List<T>, startValue: T, onSelect: (T) -> Unit) {
    val mRememberObserver = remember { mutableStateOf(startValue) }
    Column {
        mItems.forEach { mItem ->
            Row {
                RadioButton(
                    selected = mRememberObserver.value == mItem,
                    onClick = { mRememberObserver.value = mItem },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(selectedColor = Color.Magenta)
                )
            }
            Text(text = mItem.toString(), modifier = Modifier.padding(start = 8.dp))
        }
    }
    onSelect(mRememberObserver.value)
}

