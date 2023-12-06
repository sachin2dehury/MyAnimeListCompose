package github.sachin2dehury.myanimelistcompose.presentation.paginated

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterChip(modifier: Modifier = Modifier, title: String, callback: () -> Unit) {
    OutlinedButton(
        modifier = modifier
            .padding(end = 16.dp)
            .wrapContentSize(),
        colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        onClick = callback
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = title)
            Image(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Down")
        }
    }
}