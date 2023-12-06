package github.sachin2dehury.myanimelistcompose.presentation.paginated

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.PaginatedUiState

@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    state: PaginatedUiState,
    viewModel: PaginatedViewModel
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp, 12.dp)
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.Start,
    ) {
        var dialogType by remember {
            mutableStateOf<String?>(null)
        }
        OutlinedButton(
            modifier = Modifier
                .padding(end = 16.dp)
                .wrapContentSize(),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            onClick = {
                dialogType = "Sorting basis"
            }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = state.sortingBasis)
                Image(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Down")
            }
        }

        OutlinedButton(
            modifier = Modifier
                .padding(end = 16.dp)
                .wrapContentSize(),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            onClick = {
                dialogType = "Type"
            }) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = state.type)
                Image(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Down")
            }
        }
        OutlinedButton(
            modifier = Modifier
                .wrapContentSize(),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            onClick = {
                dialogType = "Rating"
            }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = state.rating)
                Image(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "Down")
            }
        }

        if (!dialogType.isNullOrEmpty()) {
            val (selected, map) = when (dialogType) {
                "Sorting basis" -> state.sortingBasis to FilterUiModel.SORTING_BASIS
                "Type" -> state.type to FilterUiModel.TYPE
                "Rating" -> state.rating to FilterUiModel.RATING
                else -> state.sortingBasis to FilterUiModel.SORTING_BASIS
            }
            FilterDialog(selected = selected,
                map = map,
                onDismissCallback = { dialogType = null }) {
                dialogType = null
                when (map) {
                    FilterUiModel.SORTING_BASIS -> viewModel.updateState(
                        viewModel.state.value.copy(
                            sortingBasis = it
                        )
                    )

                    FilterUiModel.TYPE -> viewModel.updateState(
                        viewModel.state.value.copy(
                            type = it
                        )
                    )

                    FilterUiModel.RATING -> viewModel.updateState(
                        viewModel.state.value.copy(
                            rating = it
                        )
                    )
                }
            }
        }
    }
}