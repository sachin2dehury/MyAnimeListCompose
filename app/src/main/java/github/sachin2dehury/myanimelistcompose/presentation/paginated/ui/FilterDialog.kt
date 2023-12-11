package github.sachin2dehury.myanimelistcompose.presentation.paginated.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun FilterDialog(
    modifier: Modifier = Modifier,
    mExpanded: Boolean = true,
    offset: DpOffset,
    selected: String,
    values: List<String>,
    onDismissCallback: () -> Unit,
    callback: (String) -> Unit,
) {
    var expanded by remember {
        mutableStateOf(mExpanded)
    }
    DropdownMenu(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .wrapContentSize(),
        expanded = expanded,
        offset = offset,
        onDismissRequest = {
            expanded = false
            onDismissCallback.invoke()
        },
    ) {
        values.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        callback.invoke(it)
                        expanded = false
                    }
                    .padding(end = 16.dp)
                    .clip(RoundedCornerShape(16.dp)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(selected = selected == it, onClick = {
                    callback.invoke(it)
                    expanded = false
                })
                Text(text = it)
            }
        }
    }
}
