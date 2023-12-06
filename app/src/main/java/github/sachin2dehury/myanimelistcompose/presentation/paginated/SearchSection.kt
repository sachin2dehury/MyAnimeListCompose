package github.sachin2dehury.myanimelistcompose.presentation.paginated

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    query: String,
    viewModel: PaginatedViewModel,
    scrollBehavior: TopAppBarScrollBehavior
) {
    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        title = {
            BasicTextField(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 0.dp),
                value = query,
                textStyle = MaterialTheme.typography.bodyLarge,
                onValueChange = {
                    viewModel.updateState(viewModel.state.value.copy(query = it))
                },
                singleLine = true,
            )
        },
        navigationIcon = {
            Image(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = "Search"
            )
        },
        actions = {
            if (query.isNotEmpty()) {
                Image(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .clickable {
                            viewModel.updateState(viewModel.state.value.copy(query = null))
                        },
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Clear"
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}