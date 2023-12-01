package github.sachin2dehury.myanimelistcompose.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import github.sachin2dehury.myanimelistcompose.presentation.ErrorSection

@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    id: Int,
    viewModel: DetailViewModel = hiltViewModel()
) {
    Box(modifier = modifier) {
        LaunchedEffect(key1 = Unit, block = {
            viewModel.getDetail(id)
        })
        val state = viewModel.state.collectAsState().value
        if (state.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (!state.error.isNullOrEmpty()) {
            ErrorSection(error = state.error) {
                viewModel.getDetail(id)
            }
        }
        if (state.data != null) {
            DetailSection(data = state.data)
        }
    }
}

