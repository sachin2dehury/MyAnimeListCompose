package github.sachin2dehury.myanimelistcompose.presentation.paginated.ui

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import github.sachin2dehury.myanimelistcompose.presentation.paginated.PaginatedViewModel
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.FilterUtil
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.PaginatedUiState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FilterSection(
    modifier: Modifier = Modifier,
    state: PaginatedUiState,
    viewModel: PaginatedViewModel,
    filters: List<String> = FilterUtil.filterList,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        var dialogType by remember {
            mutableStateOf<String?>(null)
        }
        filters.forEach {
            FilterChip(
                title = FilterUtil.getStateValue(it, state, false),
            ) {
                dialogType = it
            }
        }

        if (!dialogType.isNullOrEmpty()) {
            FilterDialog(
                offset = DpOffset.Zero,
                selected = FilterUtil.getStateValue(dialogType.orEmpty(), state),
                values = FilterUtil.getValues(dialogType.orEmpty()),
                onDismissCallback = { dialogType = null },
            ) {
                val newState = FilterUtil.updateFilterState(dialogType.orEmpty(), state, it)
                viewModel.updateState(newState)
                dialogType = null
            }
        }
    }
}
