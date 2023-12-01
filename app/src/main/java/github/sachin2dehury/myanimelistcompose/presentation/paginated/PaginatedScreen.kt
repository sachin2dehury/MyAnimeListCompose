package github.sachin2dehury.myanimelistcompose.presentation.paginated

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import github.sachin2dehury.myanimelistcompose.presentation.ErrorSection

@Composable
fun PaginatedScreen(
    modifier: Modifier = Modifier,
    viewModel: PaginatedViewModel = hiltViewModel(),
    navController: NavHostController
) {
    Box(modifier = modifier) {
        val state = viewModel.state.collectAsState().value
        val pagingState = viewModel.pager.collectAsLazyPagingItems()
        val loadState = pagingState.loadState
        if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading) {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        (loadState.refresh as? LoadState.Error)?.let {
            ErrorSection(error = it.error.localizedMessage ?: "Something went wrong!!") {
                pagingState.refresh()
            }
        }
        (loadState.append as? LoadState.Error)?.let {
            ErrorSection(error = it.error.localizedMessage ?: "Something went wrong!!") {
                pagingState.retry()
            }
        }
        val itemCount = pagingState.itemCount
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2)
        ) {
            items(itemCount) {
                val item = pagingState[it] ?: return@items
                PaginatedSection(data = item) {
                    navController.navigate("detail/${item.malId}")
                }
            }
        }
    }
}
